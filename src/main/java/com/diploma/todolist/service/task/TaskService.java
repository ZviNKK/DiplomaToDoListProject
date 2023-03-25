package com.diploma.todolist.service.task;

import com.diploma.todolist.service.task.dto.*;

public interface TaskService {
    TaskOutputDTO createTask(CreateTaskInputDTO createTaskInputDTO);

    TaskOutputDTO updateTask(Long taskId, UpdateTaskInputDTO updateTaskInputDTO);

    DeleteTaskOutputDTO deleteTask(Long taskId);

    TaskOutputDTO getTaskById(Long taskId);

    UserTasksOutputDTO getTasksByUserId(Long userId);
}
