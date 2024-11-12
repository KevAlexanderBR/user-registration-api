package com.kevin.user_registration_api.infrastructure.exception;

import com.kevin.user_registration_api.application.dto.ApiErroDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private HttpServletRequest request;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleValidationExceptions_ShouldReturnBadRequest() {
        when(request.getRequestURI()).thenReturn("/test");
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(new FieldError("objectName", "field", "error message")));
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<ApiErroDTO> response = globalExceptionHandler.handleValidationExceptions(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getBody().status());
        assertEquals("/test", response.getBody().path());
        assertEquals("400 BAD_REQUEST", response.getBody().error());
        assertEquals("{field=error message}", response.getBody().message());
    }

    @Test
    void handleExceptions_ShouldReturnServiceUnavailable() {
        when(request.getRequestURI()).thenReturn("/test");
        Exception exception = new Exception("Service is down");

        ResponseEntity<ApiErroDTO> response = globalExceptionHandler.handleExceptions(exception, request);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals(503, response.getBody().status());
        assertEquals("/test", response.getBody().path());
        assertEquals("503 SERVICE_UNAVAILABLE", response.getBody().error());
        assertEquals("Service is down", response.getBody().message());
    }
}
