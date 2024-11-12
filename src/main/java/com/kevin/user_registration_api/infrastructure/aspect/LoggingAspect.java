package com.kevin.user_registration_api.infrastructure.aspect;

import com.kevin.user_registration_api.domain.ports.in.LogService;
import com.kevin.user_registration_api.infrastructure.aspect.context.RequestContext;
import com.kevin.user_registration_api.infrastructure.serializer.JsonSerializer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    private final LogService logService;
    private final JsonSerializer jsonSerializer;
    private final RequestContext requestContext;
    private final HttpServletRequest request;

    public LoggingAspect(LogService logService, JsonSerializer jsonSerializer, RequestContext requestContext, HttpServletRequest request) {
        this.logService = logService;
        this.jsonSerializer = jsonSerializer;
        this.requestContext = requestContext;
        this.request = request;
    }

    @Around("@within(org.springframework.web.bind.annotation.RequestMapping)")
    public Object logHttpRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        String requestBody = getRequestBody(joinPoint);
        requestContext.setBody(requestBody);

        Object responseObject = joinPoint.proceed();

        String responseBody = jsonSerializer.serializeJson(responseObject);
        logService.log(
                request.getRequestURI(),
                request.getMethod(),
                requestBody,
                ((ResponseEntity<?>) responseObject).getStatusCode().toString(),
                responseBody
        );

        return responseObject;
    }

    @Around("@within(org.springframework.web.bind.annotation.ControllerAdvice)")
    public Object logHttpRequestResponseException(ProceedingJoinPoint joinPoint) throws Throwable {
        String requestBody = requestContext.getBody();
        requestBody = requestBody == null ? getRequestBody(joinPoint) : requestBody;
        Object responseObject = joinPoint.proceed();

        String responseBody = jsonSerializer.serializeJson(responseObject);
        logService.log(
                request.getRequestURI(),
                request.getMethod(),
                requestBody,
                ((ResponseEntity<?>) responseObject).getStatusCode().toString(),
                responseBody
        );

        return responseObject;
    }

    private String getRequestBody(ProceedingJoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs())
                .filter(arg -> !(arg instanceof HttpServletRequest || arg instanceof HttpServletResponse))
                .findFirst()
                .map(jsonSerializer::serializeJson)
                .orElse("");
    }

}
