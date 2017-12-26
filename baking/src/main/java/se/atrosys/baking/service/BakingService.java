package se.atrosys.baking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.baking.client.IngredientClient;
import se.atrosys.baking.client.RecipeClient;
import se.atrosys.baking.model.BakingResult;
import se.atrosys.baking.model.IngredientsUpdateResult;
import se.atrosys.baking.model.Recipe;

import java.util.Collections;
import java.util.stream.Collectors;

/**
 * TODO write documentation
 */
@Component
public class BakingService {
	private final RecipeClient recipeClient;
	private final IngredientClient ingredientClient;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public BakingService(RecipeClient recipeClient, IngredientClient ingredientClient) {
		this.recipeClient = recipeClient;
		this.ingredientClient = ingredientClient;
	}

	public BakingResult bake(String recipe) {
		logger.info("Baking {}", recipe);
		Recipe r = recipeClient.getRecipe(recipe);

		if (r == null) {
			logger.warn("Recipe not found: {}", recipe);

			return BakingResult.builder()
					.success(false)
					.recipe(recipe)
					.errors(Collections.singletonList("No such recipe found"))
					.build();
		}

		// TODO this should be more user-friendly
		if (!getIngredients(r)) {
			logger.warn("Ingredients missing");
			return BakingResult.builder()
					.success(false)
					.recipe(recipe)
					.errors(Collections.singletonList("Ingredients missing"))
					.build();

		}

//		subtractIngredients(r);

		logger.info("Great success!");

		return BakingResult.builder()
				.recipe(recipe)
				.success(true)
				.build();
	}

	private boolean getIngredients(Recipe r) {
		final String s = r.getIngredients()
				.entrySet()
				.stream()
				.map(entry -> entry.getKey() + " " + entry.getValue())
				.collect(Collectors.joining(", "));

		logger.info("Getting ingredients: {}", s);
		IngredientsUpdateResult result = ingredientClient.pickOutIngredients(r.getIngredients());
		return result.isSuccessful();
	}

}
