package se.atrosys.baking.model;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * TODO write documentation
 */
@Builder
@Data
public class StoreRequest {
	private Integer recipeId;
	private ZonedDateTime bakedAt;
}
