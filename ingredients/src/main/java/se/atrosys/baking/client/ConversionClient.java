package se.atrosys.baking.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se.atrosys.baking.model.ConversionRequest;
import se.atrosys.baking.model.IngredientUnitAmount;

/**
 * TODO write documentation
 */
@FeignClient("conversion")
public interface ConversionClient {
//	@GetMapping("/convert/{ingredient}")
//	IngredientTypeAmount convert(@PathVariable String ingredient, IngredientTypeAmount params);

	@PutMapping("/subtract")
	IngredientUnitAmount subtract(@RequestBody ConversionRequest request);
}
