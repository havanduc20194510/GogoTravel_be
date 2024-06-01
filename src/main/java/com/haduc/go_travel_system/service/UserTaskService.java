package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.CreateTaskRequest;
import com.haduc.go_travel_system.dto.request.UpdateTaskRequest;
import com.haduc.go_travel_system.dto.request.UserTaskRequest;
import com.haduc.go_travel_system.dto.response.UserTaskResponse;
import com.haduc.go_travel_system.mapper.UserTaskMapper;

import java.util.List;

public interface UserTaskService {
    UserTaskResponse createTask(UserTaskRequest request);

    UserTaskResponse getTaskById(Long id);

    UserTaskResponse updateTask(Long id, UserTaskRequest request);

    String deleteTask(Long id);

    List<UserTaskResponse> getAllTasks();

    List<UserTaskResponse> getTasksByEmail(String email);

    List<UserTaskResponse> getTasksByPhone(String phone);

    List<UserTaskResponse> getMyTasks();

    String verifyTask(String userTaskId);

    List<UserTaskResponse> getTasksByEmailOrPhone(String email, String phone);

}
