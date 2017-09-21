package se.atrosys.baking.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.atrosys.baking.model.Recipe;

import java.util.List;

/**
 * TODO write documentation
 */
@FeignClient("recipes")
public interface RecipeClient {
	@RequestMapping(method = RequestMethod.GET, value = "/recipe")
	List<Recipe> getRecipes();

	@RequestMapping(method = RequestMethod.GET, value = "/recipe/{recipe}")
	Recipe getRecipe(@PathVariable("recipe") String recipe);

//	@RequestMapping(method = RequestMethod.GET, value = "/recipe/{recipeId}")
//	Recipe getSpecificRecipe(@PathVariable("recipeId") String recipeId);
}
