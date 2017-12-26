package se.atrosys.baking.conversion.config;

import org.springframework.context.annotation.Configuration;
import se.atrosys.baking.conversion.model.Ratio;
import se.atrosys.baking.conversion.repository.UnitRepository;

import javax.annotation.PostConstruct;

import static se.atrosys.baking.model.Unit.*;

/**
 * TODO write documentation
 */
@Configuration
public class PopulateConfig {
	private final UnitRepository repository;

	public PopulateConfig(UnitRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	public void populate() {
		repository.save(Ratio.of(null, TEASPOONS, MILLILITRES, 5.0));
		repository.save(Ratio.of(null, TABLESPOONS, MILLILITRES, 15.0));
		repository.save(Ratio.of(null, LITRES, MILLILITRES, 1000.0));
		repository.save(Ratio.of(null, DECILITRES, MILLILITRES, 100.0));
		repository.save(Ratio.of(null, CENTILITRES, MILLILITRES, 10.0));
	}
}
