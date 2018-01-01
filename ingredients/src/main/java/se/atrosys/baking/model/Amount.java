package se.atrosys.baking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

/**
 * TODO write documentation
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Amount {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Min(0)
	private Long amount;
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
//	@MapsId
	@JsonIgnore
	private StoredIngredient ingredient;

	public static Amount from(IngredientUnitAmount typeAmount) {
		return Amount.builder()
			.amount(Math.round(typeAmount.getAmount()))
			.build();
	}
}
