package com.example.demo.exception;

import lombok.Getter;

@Getter
public class ApiValidationException extends RuntimeException {

    private final String message;

    public ApiValidationException(String message) {
        this.message = message;
    }
}
