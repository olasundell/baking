package se.atrosys.baking.conversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO write documentation
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("se.atrosys.baking.model")
public class ConversionApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConversionApplication.class, args);
	}
}
