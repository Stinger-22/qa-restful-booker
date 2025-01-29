package com.restfulbooker.api.requests.exceptions;

public class AuthEndpointNotWorkingException extends RuntimeException {
    public AuthEndpointNotWorkingException(String message) {
        super(message);
    }
}
