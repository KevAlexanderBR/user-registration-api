package com.kevin.user_registration_api.infrastructure.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springdoc.core.models.GroupedOpenApi;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigTest {

    private SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        swaggerConfig = new SwaggerConfig();
    }

    @Test
    void testPublicApi() {
        GroupedOpenApi groupedOpenApi = swaggerConfig.publicApi();

        assertNotNull(groupedOpenApi);
        assertEquals("api", groupedOpenApi.getGroup());
    }

    @Test
    void testOpenAPI() {
        OpenAPI openAPI = swaggerConfig.openAPI();

        assertNotNull(openAPI);
        assertNotNull(openAPI.getInfo());
        assertEquals("User Registration API", openAPI.getInfo().getTitle());
        assertEquals("1.0", openAPI.getInfo().getVersion());

        assertNotNull(openAPI.getComponents());
        assertNotNull(openAPI.getComponents().getSecuritySchemes());
        assertTrue(openAPI.getComponents().getSecuritySchemes().containsKey("bearerAuth"));

        SecurityScheme securityScheme = openAPI.getComponents().getSecuritySchemes().get("bearerAuth");
        assertEquals(SecurityScheme.Type.HTTP, securityScheme.getType());
        assertEquals("bearer", securityScheme.getScheme());
        assertEquals("JWT", securityScheme.getBearerFormat());

        assertNotNull(openAPI.getSecurity());
        assertFalse(openAPI.getSecurity().isEmpty());

        SecurityRequirement securityRequirement = openAPI.getSecurity().get(0);
        assertTrue(securityRequirement.containsKey("bearerAuth"));
    }
}
