package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateTaskRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.TaskResponse;
import com.haduc.go_travel_system.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {
    private TaskService taskService;
    @PostMapping("/create")
    public ApiResponse<TaskResponse> createTask(@RequestBody CreateTaskRequest request) {
        TaskResponse taskResponse = taskService.createTask(request);
        return ApiResponse.<TaskResponse>builder().message("create task successfully!").data(taskResponse).build();
    }
}
