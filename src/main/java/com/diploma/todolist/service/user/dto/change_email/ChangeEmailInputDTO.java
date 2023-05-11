package com.diploma.todolist.service.user.dto.change_email;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class ChangeEmailInputDTO {
    @Email
    private String newEmail;
}
