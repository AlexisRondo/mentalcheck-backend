package com.fiap.mentalcheck.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("MentalCheck API")
                .version("1.0.0")
                .description("API REST para monitoramento de bem-estar mental em ambientes de trabalho híbrido")
                .contact(new Contact()
                    .name("FIAP - Global Solution")
                    .email("rm560384@fiap.com.br")
                )
            )
            .components(new Components()
                .addSecuritySchemes("bearer-auth",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description("Autenticação JWT - insira o token gerado no endpoint /auth/login")
                )
            );
    }
}
