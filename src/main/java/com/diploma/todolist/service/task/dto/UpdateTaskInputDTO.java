package com.diploma.todolist.service.task.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class UpdateTaskInputDTO {
    @NotBlank
    private String title;
}
