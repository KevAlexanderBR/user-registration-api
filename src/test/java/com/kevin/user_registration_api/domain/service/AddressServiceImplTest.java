package com.kevin.user_registration_api.domain.service;

import com.kevin.user_registration_api.application.exception.AddressResponseException;
import com.kevin.user_registration_api.infrastructure.out.integration.AddressResponse;
import com.kevin.user_registration_api.infrastructure.out.integration.AdressFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AddressServiceImplTest {

    @Mock
    private AdressFeignClient adressFeignClient;

    @InjectMocks
    private AddressServiceImpl addressServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAddressTest() {
        AddressResponse mockResponse = new AddressResponse();
        mockResponse.setLogradouro("Rua Mock");
        when(adressFeignClient.getAddress(anyString())).thenReturn(ResponseEntity.ok(mockResponse));

        AddressResponse response = addressServiceImpl.getAddress("12345-678");
        assertEquals("Rua Mock", response.getLogradouro());
    }

    @Test
    void getAddressExceptionTest() {
        when(adressFeignClient.getAddress(anyString())).thenThrow(new RuntimeException("Erro de conexão"));
        assertThrows(AddressResponseException.class, () -> addressServiceImpl.getAddress("12345-678"));
    }

}