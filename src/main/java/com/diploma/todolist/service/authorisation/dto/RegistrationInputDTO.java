package com.diploma.todolist.service.authorisation.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class RegistrationInputDTO {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
    private String phone;
}
