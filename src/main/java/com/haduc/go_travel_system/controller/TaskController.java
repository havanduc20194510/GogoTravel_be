package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateTaskRequest;
import com.haduc.go_travel_system.dto.request.UpdateTaskRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.TaskResponse;
import com.haduc.go_travel_system.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {
    private final TaskService taskService;
    @PostMapping("/create")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<TaskResponse> createTask(@RequestBody CreateTaskRequest request) {
        TaskResponse taskResponse = taskService.createTask(request);
        return ApiResponse.<TaskResponse>builder().message("create task successfully!").data(taskResponse).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<TaskResponse> getTaskById(@PathVariable Long id) {
        TaskResponse taskResponse = taskService.getTaskById(id);
        return ApiResponse.<TaskResponse>builder().message("get task successfully!").data(taskResponse).build();
    }

    @GetMapping("/all")
    public ApiResponse<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> taskResponses = taskService.getAllTasks();
        return ApiResponse.<List<TaskResponse>>builder().message("get all tasks successfully!").data(taskResponses).build();
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<String> deleteTask(@PathVariable Long id) {
        String message = taskService.deleteTask(id);
        return ApiResponse.<String>builder().message(message).build();
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<TaskResponse> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        TaskResponse taskResponse = taskService.updateTask(id, request);
        return ApiResponse.<TaskResponse>builder().message("update task successfully!").data(taskResponse).build();
    }

    @GetMapping("/tour-schedule/{tourScheduleId}")
    public ApiResponse<List<TaskResponse>> getTasksByTourScheduleId(@PathVariable Long tourScheduleId) {
        List<TaskResponse> taskResponses = taskService.getTasksByTourScheduleId(tourScheduleId);
        return ApiResponse.<List<TaskResponse>>builder().message("get tasks by tour schedule id successfully!").data(taskResponses).build();
    }

}
