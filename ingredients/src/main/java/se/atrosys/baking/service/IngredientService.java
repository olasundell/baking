package se.atrosys.baking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import se.atrosys.baking.model.Amount;
import se.atrosys.baking.model.StoredIngredient;
import se.atrosys.baking.model.IngredientsUpdateResult;
import se.atrosys.baking.repository.IngredientRepository;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO write documentation
 */
@Component
public class IngredientService {
	private final IngredientRepository repository;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public IngredientService(IngredientRepository repository) {
		this.repository = repository;
	}

	public IngredientsUpdateResult save(Map<String, Integer> ingredientNameValue) {
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
	void saveTransactionally(Map<String, Integer> ingredientNameValue) {
		List<StoredIngredient> ingredients = ingredientNameValue.entrySet()
				.stream()
				.map(entry -> {
					StoredIngredient in = repository.findByName(entry.getKey());
					final Amount amount = in.getAmount();
					final Integer oldAmount = amount.getAmount();
					amount.setAmount(amount.getAmount() - entry.getValue());
					logger.info("Ingredient {} used to have {} units, now has {}", in.getName(), oldAmount, amount.getAmount());
					return in;
				}).collect(Collectors.toList());

		repository.save(ingredients);
	}
}
