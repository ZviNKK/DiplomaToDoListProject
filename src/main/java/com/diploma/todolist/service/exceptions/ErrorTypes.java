package com.diploma.todolist.service.exceptions;

import lombok.Getter;

@Getter
public enum ErrorTypes {
    USER_ALREADY_EXIST("user_already_exist_exception", "User already exist"),
    INCORRECT_LOGIN_OR_PASSWORD("incorrect_login_or_password_exception", "Incorrect login or password"),
    USER_NOT_FOUND("user_not_found_exception", "User not found"),
    TASK_NOT_FOUND("task_not_found_exception", "Task not found");

    private final String code;
    private final String description;

    ErrorTypes(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

