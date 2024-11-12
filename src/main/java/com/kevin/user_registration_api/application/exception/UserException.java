package com.kevin.user_registration_api.application.exception;

public class UserException extends RuntimeException {

    public UserException(String message) {
        super("UserException: " + message);
    }
}
