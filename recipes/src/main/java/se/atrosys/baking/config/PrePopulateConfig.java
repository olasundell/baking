package se.atrosys.baking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import se.atrosys.baking.model.Recipe;
import se.atrosys.baking.repository.RecipeRepository;

import javax.annotation.PostConstruct;

/**
 * TODO write documentation
 */
@Configuration
public class PrePopulateConfig {
	private final RecipeRepository recipeRepository;

	@Autowired
	public PrePopulateConfig(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@PostConstruct
	public void populate() {
		recipeRepository.save(Recipe.builder()
				.name("Sockerkaka")
				.ingredient("baking soda", 20)
				.ingredient("sugar", 500)
				.ingredient("egg", 4)
				.ingredient("wheat flour", 1_000)
				.build());
	}
}
