package com.diploma.todolist.service.task.impl;

import com.diploma.todolist.adaptor.persistence.TaskRepository;
import com.diploma.todolist.adaptor.persistence.UserRepository;
import com.diploma.todolist.service.exceptions.error_code.not_found.UserNotFoundException;
import com.diploma.todolist.service.task.TaskService;
import com.diploma.todolist.service.task.dto.CreateTaskInputDTO;
import com.diploma.todolist.service.task.dto.CreateTaskOutputDTO;
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
    public CreateTaskOutputDTO createTask(CreateTaskInputDTO createTaskInputDTO) {
        final var userId = createTaskInputDTO.getUser().getId();
        log.error("Id: {}", userId);
        if (!userRepository.findById(userId).isPresent()) {
            log.error("User not found. User id: {}. Input data: {}", userId, createTaskInputDTO);
            throw new UserNotFoundException();
        }

        final var task = mapper.toTask(createTaskInputDTO);
        taskRepository.save(task);
        return mapper.toOutputTask(task);
    }
}
