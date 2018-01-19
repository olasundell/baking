package se.atrosys.baking.storage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

/**
 * TODO write documentation
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoredGoods {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Integer recipeId;
	private ZonedDateTime bakedAt;
}
