package com.kevin.user_registration_api.domain.ports.out;

import com.kevin.user_registration_api.infrastructure.out.integration.AddressResponse;

public interface AddressService {

    AddressResponse getAddress(String postalCode);
}
