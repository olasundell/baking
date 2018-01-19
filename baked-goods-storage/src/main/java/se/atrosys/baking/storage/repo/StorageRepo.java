package se.atrosys.baking.storage.repo;

import org.springframework.data.repository.CrudRepository;
import se.atrosys.baking.storage.model.StoredGoods;

/**
 * TODO write documentation
 */
public interface StorageRepo extends CrudRepository<StoredGoods, Long> {
}
