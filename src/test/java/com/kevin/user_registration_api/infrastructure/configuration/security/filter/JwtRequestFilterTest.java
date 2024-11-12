package com.kevin.user_registration_api.infrastructure.configuration.security.filter;

import com.kevin.user_registration_api.infrastructure.security.JwtTokenProviderImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JwtRequestFilterTest {

    @InjectMocks
    private JwtRequestFilter jwtRequestFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;

    @Mock
    private WebAuthenticationDetailsSource detailsSource;

    private String key = "1LEz8OwNj5fNQI2n6uHnSIi0mMhoYBQ36+JTMkRSBps=";

    private String token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtRequestFilter, "key", key);
        token = Jwts.builder()
                .setSubject("testUser")
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    @Test
    void doFilterInternal() throws ServletException, IOException {
        String validJwt = token;
        String username = "testUser";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + validJwt);

        jwtRequestFilter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(username, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Test
    void doFilterInternalInvalidJwtTest() throws ServletException, IOException {
        String invalidJwt = token + "invalid";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + invalidJwt);
        assertDoesNotThrow(() -> jwtRequestFilter.doFilterInternal(request, response, chain));
    }
}
