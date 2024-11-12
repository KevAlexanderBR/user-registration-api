package com.kevin.user_registration_api.infrastructure.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtTokenProviderImplTest {

    @InjectMocks
    private JwtTokenProviderImpl jwtTokenProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtTokenProvider, "key", "1LEz8OwNj5fNQI2n6uHnSIi0mMhoYBQ36+JTMkRSBps=");
    }

    @Test
    void generateTokenTest() {
        String token = jwtTokenProvider.generateToken();
        assertNotNull(token);
        assertTrue(token.startsWith("eyJhbGciOiJIUzI1NiJ9"));
        assertTrue(token.contains("."));
    }

}
