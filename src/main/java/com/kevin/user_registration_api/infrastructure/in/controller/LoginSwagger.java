package com.kevin.user_registration_api.infrastructure.in.controller;

import com.kevin.user_registration_api.application.dto.LoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Login", description = "Endpoint relacionado à autenticação de usuários")
public interface LoginSwagger {

    @Operation(summary = "Autenticar o usuário",
            description = "Autentica o usuário com as credenciais fornecidas e retorna um token JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autenticação bem-sucedida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginDTO.class)))
    })
    ResponseEntity<LoginDTO> login();
}
