package com.softway.diagnostic.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI hospitalDiagnosticAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Diagnostic Demo")
                        .description("API pour le système d'auto-diagnostic de l'hôpital du futur. " +
                                "Traite les index de santé et gère les queues de patients pour cardiologie et traumatologie.")
                        .version("1.0.0")
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
