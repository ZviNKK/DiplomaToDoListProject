package com.diploma.todolist.service.task.mapper;

import com.diploma.todolist.adaptor.persistence.domain.Task;
import com.diploma.todolist.service.task.dto.CreateTaskInputDTO;
import com.diploma.todolist.service.task.dto.TaskOutputDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface TaskMapper {
    Task toTask(CreateTaskInputDTO createTaskInputDTO);

    TaskOutputDTO toOutputTask(Task task);

    List<TaskOutputDTO> toOutputTasksList(List<Task> tasks);
}
