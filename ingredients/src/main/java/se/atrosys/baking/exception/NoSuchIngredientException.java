package se.atrosys.baking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * TODO write documentation
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchIngredientException extends IngredientException {
	public NoSuchIngredientException(String ingredientName) {
		super("No such ingredient: " + ingredientName);
	}
}
