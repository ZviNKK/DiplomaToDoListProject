package com.diploma.todolist.adaptor.api;

import com.diploma.todolist.service.task.TaskService;
import com.diploma.todolist.service.task.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService service;

    @PostMapping
    @Operation(summary = "Create task")
    TaskOutputDTO createTask(@RequestBody @Valid CreateTaskInputDTO createTaskInputDTO) {
        return service.createTask(createTaskInputDTO);
    }

    @PostMapping("/{taskId}")
    @Operation(summary = "Change task status")
    TaskOutputDTO changeTaskStatus(@PathVariable Long taskId) {
        return service.changeTaskStatus(taskId);
    }

    @PatchMapping("/{taskId}")
    @Operation(summary = "Update task")
    TaskOutputDTO updateTask(@PathVariable Long taskId, @RequestBody @Valid UpdateTaskInputDTO updateTaskInputDTO) {
        return service.updateTask(taskId, updateTaskInputDTO);
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "Delete task")
    DeleteTaskOutputDTO deleteTask(@PathVariable Long taskId) {
        return service.deleteTask(taskId);
    }

    @GetMapping("/{taskId}")
    @Operation(summary = "Get task by task id")
    TaskOutputDTO getTaskById(@PathVariable Long taskId) {
        return service.getTaskById(taskId);
    }

    @GetMapping
    @Operation(summary = "Get tasks by user id")
    UserTasksOutputDTO getTasksByUserId(@RequestParam Long userId) {
        return service.getTasksByUserId(userId);
    }
}
