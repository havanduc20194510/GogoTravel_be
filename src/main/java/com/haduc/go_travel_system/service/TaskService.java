package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.CreateTaskRequest;
import com.haduc.go_travel_system.dto.request.UpdateTaskRequest;
import com.haduc.go_travel_system.dto.response.TaskResponse;
import com.haduc.go_travel_system.dto.response.TaskVerifyResponse;
import com.haduc.go_travel_system.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TaskService{
    TaskResponse createTask(CreateTaskRequest request);

    TaskResponse getTaskById(Long id);

    TaskResponse updateTask(Long id, UpdateTaskRequest request);

    String deleteTask(Long id);

    List<TaskResponse> getAllTasks();

    List<TaskResponse> getTasksByTourScheduleId(Long tourScheduleId);


}
