package com.kevin.user_registration_api.application.dto;

import com.kevin.user_registration_api.domain.model.UserDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Representação de um usuário")
public record UserDTO(
        @Schema(description = "Id do usuario", example = "1")
        Long id,
        @Schema(description = "Nome do usuário", example = "Kevin")
        @NotBlank(message = "nome e obrigatorio")
        String name,
        @NotBlank(message = "email e obrigatorio")
        @Email(message = "email inválido")
        @Schema(description = "Email do usuário", example = "usuario@example.com")
        String email,
        @Schema(description = "Endereco do usuário", example = "{\"postalCode\": \"02020202\",\"number\": \"31\",\"additionalInfo\": \"Casa 04\"}")
        @NotNull(message = "endereco e obrigatorio")
        @Valid
        AddressDTO address
) {
    public UserDomain toDomain() {
        return new UserDomain(id, name, email, address.toDomain());
    }

    public static UserDTO fromDomain(UserDomain userDomain) {
        return new UserDTO(userDomain.getId(), userDomain.getName(), userDomain.getEmail(), AddressDTO.fromDomain(userDomain.getAddress()));
    }
}
