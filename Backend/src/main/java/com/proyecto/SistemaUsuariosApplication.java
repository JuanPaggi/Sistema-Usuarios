package com.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
public class SistemaUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaUsuariosApplication.class, args);
	}

	@Configuration
	@Profile("default")
	@PropertySources(value = {
			@PropertySource("classpath:database.properties"),
	})
	static class ProfileDefault {
		
	}
}
