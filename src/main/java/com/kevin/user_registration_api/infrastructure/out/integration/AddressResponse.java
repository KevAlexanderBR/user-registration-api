package com.kevin.user_registration_api.infrastructure.out.integration;

import com.kevin.user_registration_api.domain.model.AddressDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;

    public AddressDomain toAddressDomain(Long id, String additionalInfo, String number) {
        return new AddressDomain(id, cep, logradouro, additionalInfo, bairro, number, uf, estado);
    }
}