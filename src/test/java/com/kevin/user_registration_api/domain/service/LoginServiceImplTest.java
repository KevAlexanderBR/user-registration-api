package com.kevin.user_registration_api.domain.service;

import com.kevin.user_registration_api.domain.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LoginServiceImplTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private LoginServiceImpl loginService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loginTest() {
        String mockToken = "mockedJwtToken";
        when(jwtTokenProvider.generateToken()).thenReturn(mockToken);

        String token = loginService.login();
        assertEquals(mockToken, token);
    }
}
