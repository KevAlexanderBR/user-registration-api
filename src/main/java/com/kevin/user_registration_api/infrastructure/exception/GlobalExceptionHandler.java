package com.kevin.user_registration_api.infrastructure.exception;

import com.kevin.user_registration_api.application.dto.ApiErroDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErroDTO> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        ApiErroDTO apiErro = new ApiErroDTO(LocalDateTime.now(), 400, HttpStatus.BAD_REQUEST.toString(), request.getRequestURI(), errors.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErroDTO> handleExceptions(Exception ex, HttpServletRequest request) {
        ApiErroDTO apiErro = new ApiErroDTO(LocalDateTime.now(), 503, HttpStatus.SERVICE_UNAVAILABLE.toString(), request.getRequestURI(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(apiErro);
    }
}
