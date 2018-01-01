package se.atrosys.baking.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * TODO write documentation
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StoredIngredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected String name;
	protected Unit unit;
	protected Long amount;

//	@OneToOne(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.MERGE})
//	@OneToOne(mappedBy = "ingredient", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
//	@OneToOne(mappedBy = "ingredient", fetch = FetchType.LAZY)
//	@JsonUnwrapped
//	private Amount amount;
}
