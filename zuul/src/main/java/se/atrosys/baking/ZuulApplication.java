package se.atrosys.baking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * TODO write documentation
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}
}
