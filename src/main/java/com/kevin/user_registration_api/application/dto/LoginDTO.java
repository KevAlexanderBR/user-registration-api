package com.kevin.user_registration_api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de saída para o login")
public record LoginDTO(
        @Schema(description = "Token JWT gerado", example = "eyJhbGciOiJIUzI1NiIsInR...")
        String token
) {
}

