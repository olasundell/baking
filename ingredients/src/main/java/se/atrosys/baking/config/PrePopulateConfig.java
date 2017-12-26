package se.atrosys.baking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import se.atrosys.baking.model.Amount;
import se.atrosys.baking.model.StoredIngredient;
import se.atrosys.baking.model.Unit;
import se.atrosys.baking.repository.IngredientRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * TODO write documentation
 */
@Configuration
public class PrePopulateConfig {
	private final IngredientRepository ingredientRepository;

	@Autowired
	public PrePopulateConfig(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@PostConstruct
	public void populate() {
		ingredientRepository.save(amounts());
	}

	private List<StoredIngredient> amounts() {
		return Arrays.asList(ingredient("wheat flour", 32_000, Unit.GRAMMES),
				ingredient("sugar", 23_000, Unit.GRAMMES),
				ingredient("baking soda", 500, Unit.GRAMMES),
				ingredient("egg", 3, Unit.PIECES),
				ingredient("milk", 21, Unit.LITRES));
	}

	private StoredIngredient ingredient(String name, int amount, Unit unit) {
		final Amount amount1 = Amount.builder().amount((long) amount).build();
		final StoredIngredient ingredient = StoredIngredient.builder()
				.name(name)
				.amount(amount1)
				.unit(unit)
				.build();
		amount1.setIngredient(ingredient);
		return ingredient;
	}
}
