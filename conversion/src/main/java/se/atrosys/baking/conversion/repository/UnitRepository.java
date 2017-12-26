package se.atrosys.baking.conversion.repository;

import org.springframework.data.repository.CrudRepository;
import se.atrosys.baking.conversion.model.Ratio;
import se.atrosys.baking.model.Ingredient;
import se.atrosys.baking.model.Unit;

/**
 * TODO write documentation
 */
public interface UnitRepository extends CrudRepository<Ratio, Integer> {
	Ratio findFirstByFromAndTo(Unit from, Unit two);
}
