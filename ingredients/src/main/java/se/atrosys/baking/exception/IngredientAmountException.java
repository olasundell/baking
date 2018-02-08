package se.atrosys.baking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import se.atrosys.baking.model.IngredientUnitAmount;

import java.util.Map;

/**
 * TODO write documentation
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IngredientAmountException extends IngredientException {
	public IngredientAmountException(Map<String, IngredientUnitAmount> ingredientNameValue) {
		super(ingredientNameValue.toString());
	}
}
