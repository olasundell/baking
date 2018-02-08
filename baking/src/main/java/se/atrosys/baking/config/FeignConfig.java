package se.atrosys.baking.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO write documentation
 */
@Configuration
public class FeignConfig {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	public ErrorDecoder errorDecoder() {
		return (methodKey, response) -> {
			logger.error("Error decoder: {} {}", methodKey, response);
			return null;
		};
	}
}
