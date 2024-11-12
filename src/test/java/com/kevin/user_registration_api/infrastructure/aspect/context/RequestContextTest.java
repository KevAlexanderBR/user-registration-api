package com.kevin.user_registration_api.infrastructure.aspect.context;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RequestContextTest {

    @InjectMocks
    private RequestContext requestContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void setBodyOnceTest() {
        String initialBody = "Test Body";
        String newBody = "New Body Content";

        requestContext.setBody(initialBody);
        requestContext.setBody(newBody);

        assertEquals(initialBody, requestContext.getBody());
    }

    @Test
    void getBodyBeforeSetTest() {
        assertNull(requestContext.getBody());
    }

    @Test
    void getBodyAfterSetTest() {
        String bodyContent = "Test Body";
        requestContext.setBody(bodyContent);

        assertEquals(bodyContent, requestContext.getBody());
    }
}