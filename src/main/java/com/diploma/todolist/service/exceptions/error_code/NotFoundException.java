package com.diploma.todolist.service.exceptions.error_code;

public class NotFoundException extends AbstractErrorCodeException {
    public NotFoundException(String code, String message) {
        super(code, message);
    }
}

