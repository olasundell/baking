package se.atrosys.baking.storage.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * TODO write documentation
 */
@Configuration
public class JacksonConfig {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		return objectMapperBuilder().build();
	}

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		return Jackson2ObjectMapperBuilder.json().createXmlMapper(false)
			.modules(new Jdk8Module(), new JavaTimeModule())
			.featuresToEnable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID)
			.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
			;
	}
}
