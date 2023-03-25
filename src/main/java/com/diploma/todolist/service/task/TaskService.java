package com.diploma.todolist.service.task;

import com.diploma.todolist.service.task.dto.CreateTaskInputDTO;
import com.diploma.todolist.service.task.dto.CreateTaskOutputDTO;

public interface TaskService {
    CreateTaskOutputDTO createTask(CreateTaskInputDTO createTaskInputDTO);
}
