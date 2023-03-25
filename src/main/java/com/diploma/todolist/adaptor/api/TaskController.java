package com.diploma.todolist.adaptor.api;

import com.diploma.todolist.service.task.TaskService;
import com.diploma.todolist.service.task.dto.CreateTaskInputDTO;
import com.diploma.todolist.service.task.dto.DeleteTaskOutputDTO;
import com.diploma.todolist.service.task.dto.TaskOutputDTO;
import com.diploma.todolist.service.task.dto.UpdateTaskInputDTO;
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
    TaskOutputDTO createTask(@RequestBody @Valid CreateTaskInputDTO createTaskInputDTO) {
        return service.createTask(createTaskInputDTO);
    }

    @PatchMapping("/{taskId}")
    TaskOutputDTO updateTask(@PathVariable Long taskId, @RequestBody @Valid UpdateTaskInputDTO updateTaskInputDTO) {
        return service.updateTask(taskId, updateTaskInputDTO);
    }

    @DeleteMapping("/{taskId}")
    DeleteTaskOutputDTO deleteTask(@PathVariable Long taskId) {
        return service.deleteTask(taskId);
    }
}
