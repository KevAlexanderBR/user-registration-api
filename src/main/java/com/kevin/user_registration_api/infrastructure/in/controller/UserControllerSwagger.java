package com.kevin.user_registration_api.infrastructure.in.controller;

import com.kevin.user_registration_api.application.dto.ApiErroDTO;
import com.kevin.user_registration_api.application.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Usuarios", description = "Endpoints para gerenciamento de usuários")
public interface UserControllerSwagger {

    @Operation(summary = "Listar usuários",
            description = "Retorna uma lista paginada de usuários com base nos parâmetros fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios recuperada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros de consulta inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErroDTO.class))),
            @ApiResponse(responseCode = "503", description = "Servidor indisponível",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErroDTO.class)))
    })
    ResponseEntity<Page<UserDTO>> getAllUsers(int page, int size);

    @Operation(summary = "Buscar usuário",
            description = "Busca um usuário com base nos parâmetros fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário recuperado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros de consulta inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encotrado"),
            @ApiResponse(responseCode = "503", description = "Servidor indisponível",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErroDTO.class)))
    })
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id);

    @Operation(summary = "Criar usuário",
            description = "Cria um usuário com base nos parâmetros fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Request inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErroDTO.class))),
            @ApiResponse(responseCode = "503", description = "Servidor indisponível",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErroDTO.class)))
    })
    ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO);

    @Operation(summary = "Atualizar usuário",
            description = "Atualiza um usuário com base nos parâmetros fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros de consulta inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encotrado"),
            @ApiResponse(responseCode = "503", description = "Servidor indisponível",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErroDTO.class)))
    })
    ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO);

    @Operation(summary = "Excluir usuário",
            description = "Exclui um usuário com base nos parâmetros fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros de consulta inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encotrado"),
            @ApiResponse(responseCode = "503", description = "Servidor indisponível",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErroDTO.class)))
    })
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}
