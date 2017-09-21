package se.atrosys.baking.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * TODO write documentation
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BakingResult {
	private final Boolean success;
	private final String recipe;
	private final List<String> errors;
}
