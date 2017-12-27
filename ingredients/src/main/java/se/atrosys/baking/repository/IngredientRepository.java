package se.atrosys.baking.repository;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.data.repository.PagingAndSortingRepository;
import se.atrosys.baking.model.StoredIngredient;

import java.util.Optional;

/**
 * TODO write documentation
 */
public interface IngredientRepository extends PagingAndSortingRepository<StoredIngredient, Integer> {
	@NewSpan("findIngredientByName")
	Optional<StoredIngredient> findByName(String name);
}
