package se.atrosys.baking.conversion.model;

import lombok.Value;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * TODO write documentation
 */
@Value
@Entity
public class IngredientRatio {
	@Id
	Integer id;
	String ingredient;
	@Embedded
	Ratio ratio;
}
