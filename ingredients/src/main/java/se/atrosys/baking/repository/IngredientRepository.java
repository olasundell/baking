package se.atrosys.baking.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import se.atrosys.baking.model.StoredIngredient;

/**
 * TODO write documentation
 */
public interface IngredientRepository extends PagingAndSortingRepository<StoredIngredient, Integer> {
	StoredIngredient findByName(String name);
}
