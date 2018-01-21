package se.atrosys.baking.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.baking.model.Recipe;
import se.atrosys.baking.repository.RecipeRepository;

import javax.ws.rs.Path;
import java.util.Optional;

/**
 * TODO write documentation
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"} )
public class RecipeResource {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final RecipeRepository repository;

	@Autowired
	public RecipeResource(RecipeRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/recipe")
	public Iterable<Recipe> all() {
		logger.debug("Finding all recipes");
		return repository.findAll();
	}

	@GetMapping(path = "/recipe/{recipe}")
	public Recipe one(@PathVariable("recipe") String recipe) {
		logger.debug("Finding recipe {}", recipe);
		return repository.findByName(recipe);
	}

	@GetMapping(path = "/recipeid/{id}")
	public Recipe one(@PathVariable("id") Integer id) {
		logger.debug("Finding recipe by id {}", id);
		return repository.findById(id).orElseThrow(IllegalArgumentException::new);
	}
}
