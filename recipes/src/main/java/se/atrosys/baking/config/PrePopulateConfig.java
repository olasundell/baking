package se.atrosys.baking.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import se.atrosys.baking.model.Ingredient;
import se.atrosys.baking.model.IngredientTypeAmount;
import se.atrosys.baking.model.Recipe;
import se.atrosys.baking.model.UnitType;
import se.atrosys.baking.repository.RecipeRepository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * TODO write documentation
 */
@Configuration
public class PrePopulateConfig {
	private final RecipeRepository recipeRepository;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public PrePopulateConfig(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@PostConstruct
	public void populate() {
		ObjectMapper mapper = new ObjectMapper();

//		Recipe recipe = Recipe.builder()
//			.name("Sockerkaka")
//			.ingredient("flour", IngredientTypeAmount.builder()
//				.amount(13.0)
//				.type(UnitType.DECILITRES)
//				.build())
//			.ingredient("sugar", IngredientTypeAmount.builder()
//				.amount(300.0)
//				.type(UnitType.GRAMMES)
//				.build())
//			.build();
//
//		try {
//			String str = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(recipe);
//			if (str.isEmpty()) {
//				logger.error("Empty");
//			}
//		} catch (JsonProcessingException e) {
//			logger.error("Could not write value", e);
//		}

		ClassPathResource pathResource = new ClassPathResource("recipes");
		try {
			for (File recipe: Objects.requireNonNull(pathResource.getFile().listFiles())) {
				recipeRepository.save(mapper.readValue(recipe, Recipe.class));
			}
		} catch (IOException e) {
			logger.error("Could not read recipes", e);
			throw new IllegalStateException(e);
		}

//		recipeRepository.save(Recipe.builder()
//				.name("Sockerkaka")
//				.ingredient("baking soda", IngredientTypeAmount.builder()
//					.amount(50)
//					.type(Ingredient.Type.GRAMMES)
//					.build())
//				.ingredient("sugar", 500)
//				.ingredient("egg", 4)
//				.ingredient("wheat flour", 1_000)
//				.build());
//
//		/*
//		50 g	jäst
//		150 g	smör
//		2,5 dl	mjölk
//		2,5 dl	vatten
//		2	ägg
//		1,5 dl	strösocker
//		1 msk	kardemumma, grovstött
//		0,5 tsk	salt
//		13 dl	vetemjöl
//		 */
//		recipeRepository.save(Recipe.builder()
//			.name("Kanelbullar")
//			.ingredient("yeast", 50)
//			.ingredient("butter", 150)
//			.ingredient("milk", 250)
//			.ingredient("water", 250)
//			.ingredient("egg", 2)
//			.ingredient("sugar", )
//			.build());
	}
}
