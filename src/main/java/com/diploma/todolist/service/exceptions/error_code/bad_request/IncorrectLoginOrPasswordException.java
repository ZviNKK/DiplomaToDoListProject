package com.diploma.todolist.service.exceptions.error_code.bad_request;

import com.diploma.todolist.service.exceptions.ErrorTypes;
import com.diploma.todolist.service.exceptions.error_code.BadRequestException;

public class IncorrectLoginOrPasswordException extends BadRequestException {
    public IncorrectLoginOrPasswordException() {
        super(ErrorTypes.INCORRECT_LOGIN_OR_PASSWORD.getCode(), ErrorTypes.INCORRECT_LOGIN_OR_PASSWORD.getDescription());
    }
}
