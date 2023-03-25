package com.diploma.todolist.adaptor.api;

import com.diploma.todolist.service.task.TaskService;
import com.diploma.todolist.service.task.dto.CreateTaskInputDTO;
import com.diploma.todolist.service.task.dto.CreateTaskOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService service;

    @PostMapping
    CreateTaskOutputDTO createTask(@RequestBody CreateTaskInputDTO createTaskInputDTO) {
        return service.createTask(createTaskInputDTO);
    }

}
