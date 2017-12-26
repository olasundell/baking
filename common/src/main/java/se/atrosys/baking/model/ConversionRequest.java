package se.atrosys.baking.model;

import lombok.Builder;
import lombok.Data;

/**
 * TODO write documentation
 */
@Data
@Builder
public class ConversionRequest {
	private Ingredient ingredient;
	private Double originalAmount;
	private IngredientUnitAmount toSubtract;
}
