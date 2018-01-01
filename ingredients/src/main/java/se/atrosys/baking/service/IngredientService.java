package se.atrosys.baking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import se.atrosys.baking.client.ConversionClient;
import se.atrosys.baking.model.Amount;
import se.atrosys.baking.model.ConversionRequest;
import se.atrosys.baking.model.Ingredient;
import se.atrosys.baking.model.IngredientUnitAmount;
import se.atrosys.baking.model.StoredIngredient;
import se.atrosys.baking.model.IngredientsUpdateResult;
import se.atrosys.baking.repository.IngredientRepository;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TODO write documentation
 */
@Component
public class IngredientService {
	private final IngredientRepository repository;
	private final ConversionClient converterClient;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public IngredientService(IngredientRepository repository,
	                         ConversionClient converterClient) {
		this.repository = repository;
		this.converterClient = converterClient;
	}

	public IngredientsUpdateResult save(Map<String, IngredientUnitAmount> ingredientNameValue) {
		try {
			saveTransactionally(ingredientNameValue);
		} catch (TransactionSystemException e) {

			List<String> errors = new ArrayList<>();

			if (e.getOriginalException() instanceof RollbackException &&
				e.getOriginalException().getCause() instanceof ConstraintViolationException) {

				ConstraintViolationException ex = (ConstraintViolationException)e.getOriginalException().getCause();
				errors = ex.getConstraintViolations()
						.stream()
						.map(ConstraintViolation::toString)
						.collect(Collectors.toList());
				logger.warn("Error in transaction, constraints are violated. Might be someone asking for more than in store.");
			} else {
				// TODO scream and shout
				logger.error("Error in transaction, something truly unexpected happened: {}", e.getOriginalException().getClass().getName(), e);
			}

			return IngredientsUpdateResult.builder()
					.errors(errors)
					.successful(false)
					.build();
		}

		return IngredientsUpdateResult.builder()
				.successful(true)
				.build();

	}

	@Transactional
	void saveTransactionally(Map<String, IngredientUnitAmount> ingredientNameValue) {
		List<StoredIngredient> ingredients = ingredientNameValue.entrySet()
				.stream()
				.map(this::recalculateAmount).collect(Collectors.toList());

		repository.saveAll(ingredients);
	}

	private StoredIngredient recalculateAmount(Map.Entry<String, IngredientUnitAmount> entry) {
		Optional<StoredIngredient> opt = repository.findByName(entry.getKey());
		StoredIngredient in = opt.orElseThrow(() -> new IllegalArgumentException("No such ingredient: " + entry.getKey()));
//		final Amount amount = in.getAmount();

		final Long old = in.getAmount();
		final IngredientUnitAmount subtract = converterClient.subtract(
			ConversionRequest.builder()
				.ingredient(Ingredient.builder()
					.name(in.getName())
					.unit(in.getUnit())
					.build())
				.originalAmount(Double.valueOf(old))
				.toSubtract(entry.getValue())
				.build()
		);

//		amount.setAmount(Math.round(subtract.getAmount()));
		in.setAmount(Math.round(subtract.getAmount()));

		logger.info("Ingredient {} used to have {} units, now has {}",
			in.getName(),
			old,
			in.getAmount());

		return in;
	}

	public IngredientsUpdateResult save(Collection<StoredIngredient> ingredients) {
//		ingredients.forEach(ingredient -> {
//			repository.save(ingredient);
//		});
//
		repository.saveAll(ingredients);

		return IngredientsUpdateResult.builder()
			.successful(true)
			.build();
	}
}
