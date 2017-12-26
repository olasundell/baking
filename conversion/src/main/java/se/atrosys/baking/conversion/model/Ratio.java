package se.atrosys.baking.conversion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import se.atrosys.baking.conversion.service.ConvertService;
import se.atrosys.baking.model.Unit;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * TODO write documentation
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"from", "to"})
})
@Value(staticConstructor = "of")
@Embeddable
public class Ratio {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@NonFinal Integer id;
	Unit from;
	Unit to;
	Double ratio;

/*	private Ratio(Unit from, Unit to, Double ratio) {
		this.from = from;
		this.to = to;
		this.ratio = ratio;
	}

	public static Ratio of(Unit from, Unit to, Double ratio) {
		return new Ratio(from, to, ratio);
	}*/
}
