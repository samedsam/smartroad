package com.smartroad.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "SmartRoad API",
                version = "v1",
                description = "APIs for route optimization, feedback collection, and ROI simulations.",
                contact = @Contact(name = "SmartRoad Team", email = "support@smartroad.com"),
                license = @License(name = "Apache 2.0")
        )
)
public class OpenApiConfig {
}
