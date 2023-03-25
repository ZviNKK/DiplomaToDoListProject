package com.diploma.todolist.service.task.mapper;

import com.diploma.todolist.adaptor.persistence.domain.Task;
import com.diploma.todolist.service.task.dto.CreateTaskInputDTO;
import com.diploma.todolist.service.task.dto.TaskOutputDTO;
import com.diploma.todolist.service.task.dto.UpdateTaskInputDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface TaskMapper {
    Task toTask(CreateTaskInputDTO createTaskInputDTO);

    TaskOutputDTO toOutputTask(Task task);

    Task toTask(UpdateTaskInputDTO updateTaskInputDTO);
}
