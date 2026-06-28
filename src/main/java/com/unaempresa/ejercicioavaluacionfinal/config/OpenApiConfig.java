package com.unaempresa.ejercicioavaluacionfinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;

@Configuration
public class OpenApiConfig {

	private static final String ESQUEMA_JWT = "bearerAuth";

	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI()
				.info(new Info()
						.title("API de Gestión de Preguntas")
						.version("1.0")
						.description("API REST para gestionar preguntas tipo test "
								+ "(verdadero/falso, selección única, selección múltiple) "
								+ "organizadas por temática."))
				.components(new Components()
						.addSecuritySchemes(ESQUEMA_JWT, new SecurityScheme()
								.name(ESQUEMA_JWT)
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")))
				.addSecurityItem(new SecurityRequirement().addList(ESQUEMA_JWT));
	}
}