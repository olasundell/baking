package se.atrosys.baking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO write documentation
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RecipeApplication {
	public static void main(String[] args) {
		SpringApplication.run(RecipeApplication.class, args);
	}
}
