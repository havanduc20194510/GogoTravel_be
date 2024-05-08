package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.CreateUserRequest;
import com.haduc.go_travel_system.dto.request.UpdateUserRequest;
import com.haduc.go_travel_system.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    UserResponse updateUser(String userId, UpdateUserRequest request);

    void deleteUser(String userId);

    UserResponse getUser(String id);

    List<UserResponse> getUsers();
    UserResponse getMyProfile();
}
