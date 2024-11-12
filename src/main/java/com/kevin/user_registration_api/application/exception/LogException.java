package com.kevin.user_registration_api.application.exception;

public class LogException extends RuntimeException {

    public LogException(String message) {
        super("LogException: " + message);
    }
}
