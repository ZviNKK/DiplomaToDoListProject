package com.diploma.todolist.service.authorisation.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class AuthorisationOutputDTO {
    private Long id;
    private String email;
    private String password;
    private String phone;
}
