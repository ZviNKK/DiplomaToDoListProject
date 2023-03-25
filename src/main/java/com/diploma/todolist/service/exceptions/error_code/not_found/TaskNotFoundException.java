package com.diploma.todolist.service.exceptions.error_code.not_found;

import com.diploma.todolist.service.exceptions.ErrorTypes;
import com.diploma.todolist.service.exceptions.error_code.NotFoundException;

public class TaskNotFoundException extends NotFoundException {
    public TaskNotFoundException() {
        super(ErrorTypes.TASK_NOT_FOUND.getCode(), ErrorTypes.TASK_NOT_FOUND.getDescription());
    }
}
