package se.atrosys.baking.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO write documentation
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StorageApplication {
	public static void main(String[] args) {
		SpringApplication.run(StorageApplication.class, args);
	}
}