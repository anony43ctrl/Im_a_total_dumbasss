package com.synechron.group1.onlineretail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI profileServiceOpenAPI() {
		return new OpenAPI().info(new Info().title("  ")
				.description("  ")
				.contact(new Contact().name(" ").email(" ").url(" "))
				.license(new License().name("  ").url(" ")));
	}
}
