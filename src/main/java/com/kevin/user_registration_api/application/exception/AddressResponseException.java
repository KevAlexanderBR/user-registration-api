package com.kevin.user_registration_api.application.exception;

public class AddressResponseException extends RuntimeException {

    public AddressResponseException(String message) {
        super("AddressResponseException: " + message);
    }
}