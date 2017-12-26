package se.atrosys.baking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * TODO write documentation
 */
@Builder
@Data
//@Entity
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class IngredientUnitAmount {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
	@Enumerated(value = EnumType.STRING)
	private Unit unit;
	private Double amount;
}
