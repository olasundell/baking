package se.atrosys.baking.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import se.atrosys.baking.model.Recipe;

/**
 * TODO write documentation
 */
public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Integer> {
	Recipe findByName(String recipe);
}
