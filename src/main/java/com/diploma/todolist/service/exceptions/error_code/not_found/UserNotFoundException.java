package com.diploma.todolist.service.exceptions.error_code.not_found;

import com.diploma.todolist.service.exceptions.ErrorTypes;
import com.diploma.todolist.service.exceptions.error_code.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super(ErrorTypes.USER_NOT_FOUND.getCode(), ErrorTypes.USER_NOT_FOUND.getDescription());
    }
}
