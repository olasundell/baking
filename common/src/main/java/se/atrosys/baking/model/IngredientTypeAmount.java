package se.atrosys.baking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * TODO write documentation
 */
@Builder
@Data
//@Entity
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class IngredientTypeAmount {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
	@Enumerated(value = EnumType.STRING)
	private UnitType type;
	private Double amount;
}
