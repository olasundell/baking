package se.atrosys.baking.storage.client;

/**
 * TODO write documentation
 */

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.atrosys.baking.model.Recipe;

import java.util.Optional;

@FeignClient("recipes")
public interface RecipeClient {
	@RequestMapping(method = RequestMethod.GET, path = "/recipeid/{id}")
	Recipe getRecipe(@PathVariable("id") Integer id);
}
