package com.mahatechmahi.cricsync.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI cricSyncOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("CricSync Engine API")
                .description("Backend APIs for managing cricket matches, profiles, and live ball-by-ball scoring.")
                .version("v1.0.0"));
    }
}
