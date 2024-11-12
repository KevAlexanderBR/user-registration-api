package com.kevin.user_registration_api.domain.service;

import com.kevin.user_registration_api.application.exception.AddressResponseException;
import com.kevin.user_registration_api.domain.ports.out.AddressService;
import com.kevin.user_registration_api.infrastructure.out.integration.AddressResponse;
import com.kevin.user_registration_api.infrastructure.out.integration.AdressFeignClient;

public class AddressServiceImpl implements AddressService {

    private final AdressFeignClient adressFeignClient;

    public AddressServiceImpl(AdressFeignClient adressFeignClient) {
        this.adressFeignClient = adressFeignClient;
    }

    @Override
    public AddressResponse getAddress(String postalCode) {
        try {
            return adressFeignClient.getAddress(postalCode).getBody();
        } catch (Exception e) {
            throw new AddressResponseException("Erro na request da API VIACEP. CEP: " + postalCode);
        }

    }
}
