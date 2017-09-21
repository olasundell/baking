package se.atrosys.baking.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.baking.model.IngredientsUpdateResult;
import se.atrosys.baking.model.StoredIngredient;
import se.atrosys.baking.repository.IngredientRepository;
import se.atrosys.baking.service.IngredientService;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO write documentation
 */
@RestController
public class IngredientResource {
	private final IngredientRepository repository;
	private final IngredientService service;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public IngredientResource(IngredientRepository repository,
	                          IngredientService service) {
		this.repository = repository;
		this.service = service;
	}

	@GetMapping("/ingredients")
	public Iterable<StoredIngredient> all() {
		return repository.findAll();
	}

	@GetMapping("/ingredients/{ingredient}")
	public StoredIngredient one(@PathVariable("ingredient") String ingredient) {
		return repository.findByName(ingredient);
	}

	@PutMapping("/ingredients")
	public IngredientsUpdateResult update(@RequestBody Map<String, Integer> ingredientKeysAndAmounts) {
		final String s = ingredientKeysAndAmounts
				.entrySet()
				.stream()
				.map(entry -> entry.getKey() + " " + entry.getValue())
				.collect(Collectors.joining(", "));

		logger.info("Updating ingredients: {}", s);

		logger.info("Getting ingredients: {}", s);
		return service.save(ingredientKeysAndAmounts);
	}
}
