package com.diploma.todolist.service.task.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class CreateTaskOutputDTO {
    @PositiveOrZero
    private Long id;
    @NotBlank
    private String title;
}
