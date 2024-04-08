package com.haduc.vietnam_travel_system.service;

import com.haduc.vietnam_travel_system.dto.request.CreateUserRequest;
import com.haduc.vietnam_travel_system.dto.request.UpdateUserRequest;
import com.haduc.vietnam_travel_system.entity.User;

import java.util.List;

public interface UserService {
    User createUser(CreateUserRequest request);
    User updateUser(String userId, UpdateUserRequest request);

    void deleteUser(String userId);

    User getUser(String id);

    List<User> getUsers();
}
