package com.kevin.user_registration_api.infrastructure.aspect.context;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class RequestContext {
    private String body;

    public RequestContext() {
        this.body = null;
    }

    public void setBody(String body) {
        if (this.body == null) {
            this.body = body;
        }
    }

    public String getBody() {
        return body;
    }
}
