package com.riwi.proyect.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Riwi project api",
        version = "1.0",
        description = "API for project and creation task"
        //contact = @Contact(name = "Soporte", email = "example@example.com")
),
        servers = {
                //@Server(url = "https://api.herotraining.com/v1", description = "Server of production"),
                @Server(url = "http://localhost:8080/api/v1", description = "Server local")
        }
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI() // Crea una nueva instancia de OpenAPI
                .components( // Define los componentes de la especificación OpenAPI
                        new Components().addSecuritySchemes("bearerAuth", // Agrega un esquema de seguridad llamado "bearerAuth"
                                new SecurityScheme() // Crea un nuevo esquema de seguridad
                                        .type(SecurityScheme.Type.HTTP) // Define el tipo de esquema de seguridad como HTTP
                                        .scheme("bearer") // Especifica que el esquema de autenticación es de tipo "bearer"
                                        .bearerFormat("JWT") // Indica que el formato del token es JWT (JSON Web Token)
                                        .name("Authorization"))); // Establece el nombre del encabezado para pasar el token de autorización
    }

}
