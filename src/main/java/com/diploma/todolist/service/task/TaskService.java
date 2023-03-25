package com.diploma.todolist.service.task;

import com.diploma.todolist.service.task.dto.CreateTaskInputDTO;
import com.diploma.todolist.service.task.dto.DeleteTaskOutputDTO;
import com.diploma.todolist.service.task.dto.TaskOutputDTO;
import com.diploma.todolist.service.task.dto.UpdateTaskInputDTO;

public interface TaskService {
    TaskOutputDTO createTask(CreateTaskInputDTO createTaskInputDTO);

    TaskOutputDTO updateTask(Long taskId, UpdateTaskInputDTO updateTaskInputDTO);

    DeleteTaskOutputDTO deleteTask(Long taskId);
}
