package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateTaskRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.TaskResponse;
import com.haduc.go_travel_system.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {
    private final TaskService taskService;
    @PostMapping("/create")
    public ApiResponse<TaskResponse> createTask(@RequestBody CreateTaskRequest request) {
        TaskResponse taskResponse = taskService.createTask(request);
        return ApiResponse.<TaskResponse>builder().message("create task successfully!").data(taskResponse).build();
    }
}
