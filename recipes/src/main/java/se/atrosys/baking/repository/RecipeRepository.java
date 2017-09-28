package se.atrosys.baking.repository;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.data.repository.PagingAndSortingRepository;
import se.atrosys.baking.model.Recipe;

/**
 * TODO write documentation
 */
public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Integer> {
	@NewSpan("findRecipeByName")
	Recipe findByName(String recipe);
}
