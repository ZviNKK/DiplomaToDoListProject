package com.diploma.todolist.service.exceptions.error_code;

public class BadRequestException extends AbstractErrorCodeException {
    public BadRequestException(String code, String message) {
        super(code, message);
    }
}

