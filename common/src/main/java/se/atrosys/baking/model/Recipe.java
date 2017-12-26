package se.atrosys.baking.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import java.util.Map;

/**
 * TODO write documentation
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder =  Recipe.RecipeBuilder.class)
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Singular
	@ElementCollection
	@JoinTable(name = "INGREDIENT_AMOUNT", joinColumns = @JoinColumn(name = "id"))
	@MapKeyColumn(name = "name")
	private Map<String, IngredientUnitAmount> ingredients;
	private String name;

	@JsonPOJOBuilder(withPrefix = "")
	public static class RecipeBuilder {}
}
