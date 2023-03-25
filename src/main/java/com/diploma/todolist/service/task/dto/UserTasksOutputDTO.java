package com.diploma.todolist.service.task.dto;

import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserTasksOutputDTO {
    List<TaskOutputDTO> tasks;
}
