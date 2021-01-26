package com.organio;

import com.organio.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class OrganioApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganioApplication.class, args);
	}

}
