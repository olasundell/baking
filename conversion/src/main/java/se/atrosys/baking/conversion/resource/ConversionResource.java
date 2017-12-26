package se.atrosys.baking.conversion.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.baking.conversion.service.ConvertService;
import se.atrosys.baking.model.ConversionRequest;
import se.atrosys.baking.model.IngredientUnitAmount;

/**
 * TODO write documentation
 */
@RestController
public class ConversionResource {
	private final ConvertService service;

	public ConversionResource(ConvertService service) {
		this.service = service;
	}

	@GetMapping("/convert/{ingredient}")
	public IngredientUnitAmount convert(@PathVariable String ingredient, IngredientUnitAmount params) {
		return IngredientUnitAmount.builder().build();
	}

	@PutMapping("/subtract")
	public IngredientUnitAmount subtract(@RequestBody ConversionRequest request) {
		return service.subtract(request);
	}
}
