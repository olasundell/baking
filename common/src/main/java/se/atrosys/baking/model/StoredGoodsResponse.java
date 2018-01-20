package se.atrosys.baking.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.ZonedDateTime;

/**
 * TODO write documentation
 */
@Value(staticConstructor = "of")
public class StoredGoodsResponse {
	Recipe recipe;
	ZonedDateTime bakedAt;
	Long id;
}
