package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.CreateTaskRequest;
import com.haduc.go_travel_system.dto.response.TaskResponse;
import com.haduc.go_travel_system.entity.ScheduleDetail;
import com.haduc.go_travel_system.entity.Task;
import com.haduc.go_travel_system.entity.TaskType;
import com.haduc.go_travel_system.entity.TourSchedule;
import com.haduc.go_travel_system.mapper.TaskMapper;
import com.haduc.go_travel_system.repository.TaskRepository;
import com.haduc.go_travel_system.repository.TaskTypeRepository;
import com.haduc.go_travel_system.repository.TourScheduleRepository;
import com.haduc.go_travel_system.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskMapper taskMapper;

    private TourScheduleRepository tourScheduleRepository;
    private TaskRepository taskRepository;
    private final TaskTypeRepository taskTypeRepository;

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {
        Task task = taskMapper.toTask(request);
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
}
