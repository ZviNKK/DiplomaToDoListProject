package com.diploma.todolist.service.exceptions.error_code.bad_request;

import com.diploma.todolist.service.exceptions.ErrorTypes;
import com.diploma.todolist.service.exceptions.error_code.BadRequestException;

public class UserAlreadyExistException extends BadRequestException {
    public UserAlreadyExistException() {
        super(ErrorTypes.USER_ALREADY_EXIST.getCode(), ErrorTypes.USER_ALREADY_EXIST.getDescription());
    }
}
