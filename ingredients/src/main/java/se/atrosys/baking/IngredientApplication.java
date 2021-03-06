package se.atrosys.baking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * TODO write documentation
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class IngredientApplication {
	public static void main(String[] args) {
		SpringApplication.run(IngredientApplication.class, args);
	}
}
