package se.atrosys.baking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Amount {
	@Id
	@JsonIgnore
	private Integer id;
	@Min(0)
	private Integer amount;
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JsonIgnore
	private StoredIngredient ingredient;
}
