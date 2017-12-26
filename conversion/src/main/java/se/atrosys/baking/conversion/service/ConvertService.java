package se.atrosys.baking.conversion.service;

import org.springframework.stereotype.Component;
import se.atrosys.baking.conversion.model.Ratio;
import se.atrosys.baking.conversion.repository.IngredientConversionRepository;
import se.atrosys.baking.conversion.repository.UnitRepository;
import se.atrosys.baking.model.ConversionRequest;
import se.atrosys.baking.model.Ingredient;
import se.atrosys.baking.model.IngredientUnitAmount;
import se.atrosys.baking.model.Unit;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO write documentation
 */
@Component
public class ConvertService {
	private final UnitRepository repo;
	private final IngredientConversionRepository ingredientRepo;

	public ConvertService(UnitRepository repo, IngredientConversionRepository ingredientRepo) {
		this.repo = repo;
		this.ingredientRepo = ingredientRepo;
	}

	public IngredientUnitAmount subtract(ConversionRequest request) {
		Unit origUnit = request.getIngredient().getUnit();
		Unit subUnit = request.getToSubtract().getUnit();

		double ratio;

		ratio = calcRatio(request.getIngredient(), subUnit);

		return IngredientUnitAmount.builder()
			.amount(request.getOriginalAmount() - ratio * request.getToSubtract().getAmount())
			.unit(origUnit)
			.build();
	}

	private double calcRatio(Ingredient ingredient, Unit subUnit) {
		double ratio;

		if (ingredient.equals(subUnit)) {
			ratio = 1;
		} else if (ingredient.getUnit().equals(subUnit.getType())) {
			return convert(ingredient, subUnit);
		} else {
			// find conversion from table
		}

		return ratio;
	}

	private double convert(Unit origUnit, Unit subUnit) {
		final Ratio firstByFromAndTo = repo.findFirstByFromAndTo(subUnit, origUnit);
		return firstByFromAndTo.getRatio();
//		switch (subUnit) {
//			case PIECES:
//			case GRAMMES:
//			case LITRES:
//			case CENTILITRES:
//			case DECILITRES:
//			case MILLILITRES:
//			case TABLESPOONS:
//			case TEASPOONS:
//		}
//		return 0;
	}

}
