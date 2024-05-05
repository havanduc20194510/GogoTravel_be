package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.CreateTaskRequest;
import com.haduc.go_travel_system.dto.request.UpdateTaskRequest;
import com.haduc.go_travel_system.dto.response.TaskResponse;
import com.haduc.go_travel_system.entity.Task;
import com.haduc.go_travel_system.entity.TaskType;
import com.haduc.go_travel_system.entity.TourSchedule;
import com.haduc.go_travel_system.enums.TaskStatus;
import com.haduc.go_travel_system.mapper.TaskMapper;
import com.haduc.go_travel_system.repository.TaskRepository;
import com.haduc.go_travel_system.repository.TaskTypeRepository;
import com.haduc.go_travel_system.repository.TourScheduleRepository;
import com.haduc.go_travel_system.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskMapper taskMapper;

    private final TourScheduleRepository tourScheduleRepository;
    private final TaskRepository taskRepository;
    private final TaskTypeRepository taskTypeRepository;

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {
        Task task = taskMapper.toTask(request);
        task.setStatus(TaskStatus.IN_PROGRESS);
        TourSchedule tourSchedule = tourScheduleRepository.findById(request.getTourScheduleId()).orElseThrow(() -> new RuntimeException("Schedule not found"));
        task.setTourSchedule(tourSchedule);
        TaskType taskType = taskTypeRepository.findByName(request.getTaskTypeName());
        if(taskType == null) {
            TaskType newType = new TaskType();
            newType.setName(request.getTaskTypeName());
            TaskType savedTaskType = taskTypeRepository.save(newType);
            task.setTaskType(savedTaskType);
        }else {
            task.setTaskType(taskType);
        }
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return taskMapper.toDto(task);
    }

    @Override
    public TaskResponse updateTask(Long id, UpdateTaskRequest request) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setCoin(request.getCoin());
        task.setReward(request.getReward());
        task.setDeadline(request.getDeadline());
        task.setStatus(request.getStatus());
        TaskType taskType = taskTypeRepository.findByName(request.getTaskTypeName());
        if(taskType == null) {
            TaskType newType = new TaskType();
            newType.setName(request.getTaskTypeName());
            TaskType savedTaskType = taskTypeRepository.save(newType);
            task.setTaskType(savedTaskType);
        }else {
            task.setTaskType(taskType);
        }
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public String deleteTask(Long id) {
        taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.deleteById(id);
        return "Task deleted";
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if (tasks.isEmpty()) {
            throw new RuntimeException("Task not found");
        }
        return tasks.stream().map(taskMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> getTasksByTourScheduleId(Long tourScheduleId) {
        List<Task> tasks = taskRepository.findByTourScheduleId(tourScheduleId);
        if (tasks.isEmpty()) {
            throw new RuntimeException("Task not found");
        }
        return tasks.stream().map(taskMapper::toDto).collect(Collectors.toList());
    }
}
