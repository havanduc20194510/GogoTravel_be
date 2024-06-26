package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateUserRequest;
import com.haduc.go_travel_system.dto.request.UpdateUserRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.UserResponse;
import com.haduc.go_travel_system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database"
    )

    @PostMapping("/create")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("User created successfully");
        apiResponse.setData(userService.createUser(request));
        return apiResponse;
    }

    @Operation(
            summary = "Get All Users REST API",
            description = "Get All Users REST API is used to get all users from a database"
    )
    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(){
        List<UserResponse> userResponses = userService.getUsers();
        return ApiResponse.<List<UserResponse>>builder()
                .message("All users fetched successfully")
                .data(userResponses)
                .build();
    }

    @Operation(
            summary = "Get User REST API",
            description = "Get User REST API is used to get a user by userId from a database"
    )

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId){
        UserResponse userResponse = userService.getUser(userId);
        return ApiResponse.<UserResponse>builder()
                .message("User fetched successfully")
                .data(userResponse)
                .build();
    }

    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a user by userId in a database"
    )

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UpdateUserRequest request){
        UserResponse userResponse = userService.updateUser(userId, request);
        return ApiResponse.<UserResponse>builder()
                .message("User updated successfully")
                .data(userResponse)
                .build();
    }

    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a user by userId from a database"
    )

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }

    @GetMapping("/my-profile")
    ApiResponse<UserResponse> getMyProfile(){
        UserResponse userResponse = userService.getMyProfile();
        return ApiResponse.<UserResponse>builder()
                .message("User fetched successfully")
                .data(userResponse)
                .build();
    }

}
