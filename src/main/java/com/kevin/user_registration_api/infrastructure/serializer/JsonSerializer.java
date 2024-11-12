package com.kevin.user_registration_api.infrastructure.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Component
public class JsonSerializer {

    private final ObjectMapper objectMapper;

    public JsonSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String serializeJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            if (e instanceof InvalidDefinitionException && object instanceof MethodArgumentNotValidException validationException) {
                return serializeBindingResult(validationException);
            }
            return "Erro ao capturar response body";
        }
    }

    public String serializeBindingResult(MethodArgumentNotValidException validationException) {
        try {
            return objectMapper.writeValueAsString(validationException.getBindingResult().getTarget());
        } catch (JsonProcessingException ex) {
            return "Erro ao capturar response body";
        }
    }
}
