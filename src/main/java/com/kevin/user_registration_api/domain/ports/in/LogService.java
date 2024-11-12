package com.kevin.user_registration_api.domain.ports.in;

public interface LogService {

    void log(String endpoint, String method, String parameters, String responseStatus, String response);
}
