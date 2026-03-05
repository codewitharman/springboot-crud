package com.example.springboot_crud.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Employee Management API").description("""
						A Spring Boot REST API for managing employees.

						## Features
						- Full CRUD operations on employees
						- Filter by department, search by name, filter by salary
						- H2 in-memory database with pre-loaded sample data
						- Global exception handling with meaningful error responses

						## How to Use
						1. Click any endpoint below to expand it
						2. Click **Try it out**
						3. Fill in the request body or parameters
						4. Click **Execute** to call the API
						""").version("v1.0.0")
						.contact(new Contact().name("Arman Shaikh").email("arman.shaikh@crud.com")
								.url("https://github.com/codewitharman"))
						// License info (optional)
						.license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))

				.servers(List.of(new Server().url("http://localhost:8080").description("Local Development Server"),
						new Server().url("https://api.ameyainfovision.com").description("Production Server")));
	}
}
