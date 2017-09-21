package se.atrosys.baking.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * TODO write documentation
 */
@Builder
@Data
public class IngredientsUpdateResult {
	private List<String> errors;
	private boolean successful;
}
