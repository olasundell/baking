package se.atrosys.baking.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.atrosys.baking.model.Ingredient;
import se.atrosys.baking.model.IngredientUnitAmount;
import se.atrosys.baking.model.IngredientsUpdateResult;

import java.util.List;
import java.util.Map;

/**
 * TODO write documentation
 */
@FeignClient("ingredients")
public interface IngredientClient {
	@RequestMapping(method = RequestMethod.GET, value = "/ingredients")
	List<Ingredient> getIngredients();

	@RequestMapping(method = RequestMethod.PUT, value = "/ingredients")
	IngredientsUpdateResult pickOutIngredients(Map<String, IngredientUnitAmount> ingredients);
}
