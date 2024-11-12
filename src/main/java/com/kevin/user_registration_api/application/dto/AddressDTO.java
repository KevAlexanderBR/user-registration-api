package com.kevin.user_registration_api.application.dto;

import com.kevin.user_registration_api.domain.model.AddressDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Representação de um endereço")
public record AddressDTO(
        @Schema(description = "Id do endereço", example = "1")
        Long id,
        @NotBlank(message = "cep e obrigatorio")
        @Pattern(regexp = "\\d{8}", message = "O cep deve estar no formato 00000000")
        @Schema(description = "CEP de um endereço", example = "00000000")
        @Size(min = 8, max = 8)
        String postalCode,
        @Schema(description = "Logradouro de um endereço", example = "Avenida Brasil")
        String street,
        @Schema(description = "Complemento de um endereço", example = "Casa 01")
        String additionalInfo,
        @Schema(description = "Bairro de um endereço", example = "Bairro Brasil")
        String neighborhood,
        @NotBlank(message = "numero e obrigatorio")
        @Schema(description = "Número de um endereço", example = "125")
        String number,
        @Schema(description = "UF de um endereço", example = "SP")
        String state,
        @Schema(description = "Nome de um Estado de um endereço", example = "São Paulo")
        String stateName
) {

    public AddressDomain toDomain() {
        return new AddressDomain(id, postalCode, street, additionalInfo, neighborhood, number, state, stateName);
    }

    public static AddressDTO fromDomain(AddressDomain addressDomain) {
        return new AddressDTO(addressDomain.getId(), addressDomain.getPostalCode(), addressDomain.getStreet(), addressDomain.getAdditionalInfo(),
                addressDomain.getNeighborhood(), addressDomain.getNumber(), addressDomain.getState(), addressDomain.getStateName());
    }
}
