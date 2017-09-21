package se.atrosys.baking.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * TODO write documentation
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient implements Serializable {
	@Id
	protected String name;
	protected Type type;

	public enum Type {
		GRAMMES,
		LITERS,
		PIECES
	}
}
