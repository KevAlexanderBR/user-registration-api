package com.kevin.user_registration_api.infrastructure.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonSerializerTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private JsonSerializer jsonSerializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSerializeJsonSuccess() throws Exception {
        Object objectToSerialize = new Object();
        String expectedJson = "{\"key\":\"value\"}";

        when(objectMapper.writeValueAsString(objectToSerialize)).thenReturn(expectedJson);

        String result = jsonSerializer.serializeJson(objectToSerialize);

        assertEquals(expectedJson, result);
    }

    @Test
    void testSerializeJsonInvalidDefinitionException() throws Exception {
        when(objectMapper.writeValueAsString(any())).thenThrow(InvalidDefinitionException.class);

        MethodArgumentNotValidException validationException = mock(MethodArgumentNotValidException.class);
        when(validationException.getBindingResult()).thenReturn(mock(BindingResult.class));

        String result = jsonSerializer.serializeJson(validationException);

        assertEquals("Erro ao capturar response body", result);
    }

    @Test
    void testSerializeBindingResult() throws Exception {
        MethodArgumentNotValidException validationException = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(validationException.getBindingResult()).thenReturn(bindingResult);

        Object target = new Object();
        when(bindingResult.getTarget()).thenReturn(target);
        when(objectMapper.writeValueAsString(target)).thenReturn("{\"key\":\"value\"}");

        String result = jsonSerializer.serializeBindingResult(validationException);

        assertEquals("{\"key\":\"value\"}", result);
    }

    @Test
    void testSerializeJsonErrorHandling() throws Exception {
        Object objectToSerialize = new Object();
        when(objectMapper.writeValueAsString(objectToSerialize)).thenThrow(JsonProcessingException.class);

        String result = jsonSerializer.serializeJson(objectToSerialize);

        assertEquals("Erro ao capturar response body", result);
    }
}
