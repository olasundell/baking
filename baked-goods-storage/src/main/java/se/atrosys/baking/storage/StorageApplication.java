package se.atrosys.baking.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * TODO write documentation
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class StorageApplication {
	public static void main(String[] args) {
		SpringApplication.run(StorageApplication.class, args);
	}
}
