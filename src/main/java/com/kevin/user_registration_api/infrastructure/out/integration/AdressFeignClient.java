package com.kevin.user_registration_api.infrastructure.out.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "postalCode", url = "${mock.viacep.url}")
public interface AdressFeignClient {

    @GetMapping(path = "/ws/{postalCode}/json", produces = "application/json")
    ResponseEntity<AddressResponse> getAddress(@PathVariable("postalCode") String postalCode);
}
