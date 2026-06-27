package com.unaempresa.ejercicioavaluacionfinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI()
				.info(new Info()
						.title("API de Gestión de Preguntas")
						.version("1.0")
						.description("API REST para gestionar preguntas tipo test "
								+ "(verdadero/falso, selección única, selección múltiple) "
								+ "organizadas por temática."));
	}
}