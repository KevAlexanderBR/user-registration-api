package com.kevin.user_registration_api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Representação da resposta em caso de erro")
public record ApiErroDTO(
        @Schema(description = "Horario do erro", example = "2024-01-01 17:00:00") LocalDateTime timestamp,
        @Schema(description = "Http status", example = "400") Integer status,
        @Schema(description = "Descrição do status", example = "BAD REQUEST") String error,
        @Schema(description = "Endpoint que ocorreu o erro", example = "/api/users") String path,
        @Schema(description = "Mensagem detalhada do erro", example = "Erro ao salvar usuário") String message
) {
}
