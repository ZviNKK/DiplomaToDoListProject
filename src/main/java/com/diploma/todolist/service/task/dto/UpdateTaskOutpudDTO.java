package com.diploma.todolist.service.task.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class UpdateTaskOutpudDTO {
    private Long id;
    private String title;
}
