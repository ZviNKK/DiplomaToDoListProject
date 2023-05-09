package com.diploma.todolist.service.user.dto.change_email;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class ChangeEmailInputDTO {
    private String newEmail;
}
