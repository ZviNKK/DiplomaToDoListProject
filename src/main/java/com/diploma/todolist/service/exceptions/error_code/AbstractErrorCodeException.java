package com.diploma.todolist.service.exceptions.error_code;

import lombok.Getter;

@Getter
public abstract class AbstractErrorCodeException extends RuntimeException {
    private final String code;

    protected AbstractErrorCodeException(String code, String message) {
        super(message);
        this.code = code;
    }
}

