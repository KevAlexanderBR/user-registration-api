package com.kevin.user_registration_api.infrastructure.aspect;

import com.kevin.user_registration_api.domain.ports.in.LogService;
import com.kevin.user_registration_api.infrastructure.aspect.context.RequestContext;
import com.kevin.user_registration_api.infrastructure.serializer.JsonSerializer;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoggingAspectTest {

    @InjectMocks
    private LoggingAspect loggingAspect;

    @Mock
    private LogService logService;

    @Mock
    private JsonSerializer jsonSerializer;

    @Mock
    private RequestContext requestContext;

    @Mock
    private HttpServletRequest request;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void logHttpRequestResponseTest() throws Throwable {
        when(request.getRequestURI()).thenReturn("/api/test");
        when(request.getMethod()).thenReturn(HttpMethod.GET.name());

        when(jsonSerializer.serializeJson(any())).thenReturn("serializedArg");
        when(requestContext.getBody()).thenReturn(null);

        String requestBody = "serializedArg";
        when(joinPoint.getArgs()).thenReturn(new Object[]{"arg1"});
        when(joinPoint.proceed()).thenReturn(new ResponseEntity<>("response", HttpStatus.OK));

        ResponseEntity<?> response = (ResponseEntity<?>) loggingAspect.logHttpRequestResponse(joinPoint);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(logService).log(
                "/api/test",
                HttpMethod.GET.name(),
                requestBody,
                HttpStatus.OK.toString(),
                "serializedArg"
        );
    }

    @Test
    void logHttpRequestResponseExceptionTest() throws Throwable {
        when(request.getRequestURI()).thenReturn("/api/error");
        when(request.getMethod()).thenReturn(HttpMethod.POST.name());

        when(jsonSerializer.serializeJson(any())).thenReturn("errorSerializedArg");
        when(requestContext.getBody()).thenReturn(null);

        String requestBody = "errorSerializedArg";
        when(joinPoint.getArgs()).thenReturn(new Object[]{"errorArg"});
        when(joinPoint.proceed()).thenReturn(new ResponseEntity<>("errorResponse", HttpStatus.BAD_REQUEST));

        ResponseEntity<?> response = (ResponseEntity<?>) loggingAspect.logHttpRequestResponseException(joinPoint);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(logService).log(
                "/api/error",
                HttpMethod.POST.name(),
                requestBody,
                HttpStatus.BAD_REQUEST.toString(),
                "errorSerializedArg"
        );
    }
}
