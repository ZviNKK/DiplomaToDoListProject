package com.diploma.todolist.service.authorisation.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class AuthorisationInputDTO {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
