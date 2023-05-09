package com.diploma.todolist.service.user.dto.reset_password;

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
public class ResetPasswordInputDTO {
    @NotEmpty
    @Email
    private String email;
}
