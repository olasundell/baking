package se.atrosys.baking.service;

import com.netflix.client.ClientException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.NestedServletException;
import se.atrosys.baking.client.IngredientClient;
import se.atrosys.baking.client.RecipeClient;
import se.atrosys.baking.client.StorageClient;
import se.atrosys.baking.model.BakingResult;
import se.atrosys.baking.model.IngredientsUpdateResult;
import se.atrosys.baking.model.Recipe;
import se.atrosys.baking.model.StoreRequest;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * TODO write documentation
 */
@Component
public class BakingService {
	private final RecipeClient recipeClient;
	private final IngredientClient ingredientClient;
	private final StorageClient storageClient;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public BakingService(RecipeClient recipeClient,
	                     IngredientClient ingredientClient,
	                     StorageClient storageClient) {
		this.recipeClient = recipeClient;
		this.ingredientClient = ingredientClient;
		this.storageClient = storageClient;
	}

	public BakingResult bake(String recipe) {
		logger.info("Baking {}", recipe);
		Recipe r = null;

		try {
			r = recipeClient.getRecipe(recipe);
		} catch (RuntimeException e) {
			logger.error("", e);
			throw e;
		}

		if (r == null) {
			return notFound(recipe);
		}

		// TODO this should be more user-friendly
		if (!getIngredients(r)) {
			return ingredientsMissing(recipe);
		}

		storageClient.store(StoreRequest.builder()
			.bakedAt(ZonedDateTime.now())
			.recipeId(r.getId())
			.build());

		logger.info("Great success!");

		return BakingResult.builder()
				.recipe(recipe)
				.success(true)
				.build();
	}

	private BakingResult ingredientsMissing(String recipe) {
		logger.warn("Ingredients missing");
		return BakingResult.builder()
				.success(false)
				.recipe(recipe)
				.errors(Collections.singletonList("Ingredients missing"))
				.build();
	}

	private BakingResult notFound(String recipe) {
		logger.warn("Recipe not found: {}", recipe);

		return BakingResult.builder()
				.success(false)
				.recipe(recipe)
				.errors(Collections.singletonList("No such recipe found"))
				.build();
	}

	private boolean getIngredients(Recipe r) {
		final String s = r.getIngredients()
				.entrySet()
				.stream()
				.map(entry -> entry.getKey() + " " + entry.getValue())
				.collect(Collectors.joining(", "));

		IngredientsUpdateResult result = null;

		logger.info("Getting ingredients: {}", s);
		try {
			result = ingredientClient.pickOutIngredients(r.getIngredients());
		} catch (FeignException e) {
			return false;
		}
		return result.isSuccessful();
	}
}
