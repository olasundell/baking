package se.atrosys.baking.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.baking.model.Recipe;
import se.atrosys.baking.repository.RecipeRepository;

import javax.ws.rs.Path;

/**
 * TODO write documentation
 */
@RestController
public class RecipeResource {
	private final RecipeRepository repository;

	@Autowired
	public RecipeResource(RecipeRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/recipe")
	public Iterable<Recipe> all() {
		return repository.findAll();
	}

	@GetMapping(path = "/recipe/{recipe}")
	public Recipe one(@PathVariable("recipe") String recipe) {
		return repository.findByName(recipe);
	}
}
