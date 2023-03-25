package com.diploma.todolist.service.task.dto;

import com.diploma.todolist.adaptor.persistence.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class CreateTaskInputDTO {
    @NotBlank
    private String title;
    private User user;
}
