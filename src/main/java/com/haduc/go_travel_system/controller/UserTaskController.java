package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.UserTaskRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.UserTaskResponse;
import com.haduc.go_travel_system.service.UserTaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-task")
@CrossOrigin(origins = "*")
@SecurityRequirement(name ="Bearer Authentication")
public class UserTaskController {
    private final UserTaskService userTaskService;
    @PostMapping("/create")
    public ApiResponse<UserTaskResponse> createUserTask(@RequestBody UserTaskRequest request) {
        UserTaskResponse response = userTaskService.createTask(request);
        return ApiResponse.<UserTaskResponse>builder().code(200).message("create user task success!!").data(response).build();
    }

    @GetMapping("/get-by-email")
    public ApiResponse<List<UserTaskResponse>> getUserTaskByEmail(@RequestParam String email) {
        List<UserTaskResponse> response = userTaskService.getTasksByEmail(email);
        return ApiResponse.<List<UserTaskResponse>>builder().code(200).message("get user task by email success!!").data(response).build();
    }

    @GetMapping("/get-by-phone")
    public ApiResponse<List<UserTaskResponse>> getUserTaskByPhone(@RequestParam String phone) {
        List<UserTaskResponse> response = userTaskService.getTasksByPhone(phone);
        return ApiResponse.<List<UserTaskResponse>>builder().code(200).message("get user task by phone success!!").data(response).build();
    }

    @PostMapping("/verify/{userTaskId}")
    public ApiResponse<String> verifyUserTask(@PathVariable String userTaskId) {
        String response = userTaskService.verifyTask(userTaskId);
        return ApiResponse.<String>builder().code(200).message(response).build();
    }

    @GetMapping("/get-all")
    public ApiResponse<List<UserTaskResponse>> getAllUserTask() {
        List<UserTaskResponse> response = userTaskService.getAllTasks();
        return ApiResponse.<List<UserTaskResponse>>builder().code(200).message("get all user task success!!").data(response).build();
    }

    @GetMapping("/get-my-tasks")
    public ApiResponse<List<UserTaskResponse>> getMyTasks() {
        List<UserTaskResponse> response = userTaskService.getMyTasks();
        return ApiResponse.<List<UserTaskResponse>>builder().code(200).message("get my tasks success!!").data(response).build();
    }

    @GetMapping("/get-by-phone-or-email")
    public ApiResponse<List<UserTaskResponse>> getUserTaskByPhoneOrEmail(@RequestParam(required = false) String phone, @RequestParam(required = false) String email) {
        List<UserTaskResponse> response = userTaskService.getTasksByEmailOrPhone(email, phone);
        return ApiResponse.<List<UserTaskResponse>>builder().code(200).message("get user task by phone or email success!!").data(response).build();
    }
}
