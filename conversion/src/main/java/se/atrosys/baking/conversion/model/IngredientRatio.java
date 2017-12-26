package se.atrosys.baking.conversion.model;

import lombok.Value;
import lombok.experimental.NonFinal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * TODO write documentation
 */
@Value
@Entity
public class IngredientRatio {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@NonFinal Integer id;
	String ingredient;
	@OneToOne
	Ratio ratio;
}
