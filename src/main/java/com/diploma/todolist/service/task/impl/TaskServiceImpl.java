package com.diploma.todolist.service.task.impl;

import com.diploma.todolist.adaptor.persistence.TaskRepository;
import com.diploma.todolist.adaptor.persistence.UserRepository;
import com.diploma.todolist.service.exceptions.error_code.not_found.TaskNotFoundException;
import com.diploma.todolist.service.exceptions.error_code.not_found.UserNotFoundException;
import com.diploma.todolist.service.task.TaskService;
import com.diploma.todolist.service.task.dto.CreateTaskInputDTO;
import com.diploma.todolist.service.task.dto.DeleteTaskOutputDTO;
import com.diploma.todolist.service.task.dto.TaskOutputDTO;
import com.diploma.todolist.service.task.dto.UpdateTaskInputDTO;
import com.diploma.todolist.service.task.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper mapper;
    private final UserRepository userRepository;

    @Override
    public TaskOutputDTO createTask(CreateTaskInputDTO createTaskInputDTO) {
        final var userId = createTaskInputDTO.getUser().getId();
        if (!userRepository.findById(userId).isPresent()) {
            log.error("User not found. User id: {}. Input data: {}", userId, createTaskInputDTO);
            throw new UserNotFoundException();
        }

        final var task = mapper.toTask(createTaskInputDTO);
        taskRepository.save(task);
        return mapper.toOutputTask(task);
    }

    @Override
    public TaskOutputDTO updateTask(Long taskId, UpdateTaskInputDTO updateTaskInputDTO) {
        final var task = taskRepository.findById(taskId);
        if (!task.isPresent()) {
            log.error("Task not for update not found. Task id: {}, Input data: {}", taskId, updateTaskInputDTO);
            throw new TaskNotFoundException();
        }
        task.get().setTitle(updateTaskInputDTO.getTitle());
        taskRepository.save(task.get());
        return mapper.toOutputTask(task.get());
    }

    @Override
    public DeleteTaskOutputDTO deleteTask(Long taskId) {
        final var task = taskRepository.findById(taskId);
        if (!task.isPresent()) {
            log.error("Task not for update not found. Task id: {}", taskId);
            throw new TaskNotFoundException();
        }
        taskRepository.deleteById(taskId);

        return new DeleteTaskOutputDTO("success");
    }
}
