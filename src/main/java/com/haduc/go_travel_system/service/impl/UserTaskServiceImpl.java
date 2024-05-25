package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.UserTaskRequest;
import com.haduc.go_travel_system.dto.response.UserTaskResponse;
import com.haduc.go_travel_system.entity.User;
import com.haduc.go_travel_system.entity.UserTask;
import com.haduc.go_travel_system.enums.ErrorCode;
import com.haduc.go_travel_system.enums.TaskStatus;
import com.haduc.go_travel_system.exception.AppException;
import com.haduc.go_travel_system.mapper.UserTaskMapper;
import com.haduc.go_travel_system.repository.UserRepository;
import com.haduc.go_travel_system.repository.UserTaskRepository;
import com.haduc.go_travel_system.service.UserTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserTaskServiceImpl implements UserTaskService {
    private final UserTaskRepository userTaskRepository;
    private final UserRepository userRepository;

    private final UserTaskMapper userTaskMapper;

    @Override
    public UserTaskResponse createTask(UserTaskRequest request) {
        UserTask userTask = userTaskMapper.toUserTask(request);
        userTaskRepository.save(userTask);
        return userTaskMapper.toUserTaskResponse(userTask);
    }

    @Override
    public UserTaskResponse getTaskById(Long id) {
        return null;
    }

    @Override
    public UserTaskResponse updateTask(Long id, UserTaskRequest request) {
        return null;
    }

    @Override
    public String deleteTask(Long id) {
        return null;
    }

    @Override
    public List<UserTaskResponse> getAllTasks() {
        List<UserTask> userTasks = userTaskRepository.findAll();
        return userTasks.stream().map(userTaskMapper::toUserTaskResponse).toList();
    }

    @Override
    public List<UserTaskResponse> getTasksByEmail(String email) {
        List<UserTask> userTasks = userTaskRepository.findByEmail(email);
        return userTasks.stream().map(userTaskMapper::toUserTaskResponse).toList();
    }

    @Override
    public List<UserTaskResponse> getTasksByPhone(String phone) {
        List<UserTask> userTasks = userTaskRepository.findByPhone(phone);
        return userTasks.stream().map(userTaskMapper::toUserTaskResponse).toList();
    }

    @Override
    public String verifyTask(String userTaskId) {
        Optional<UserTask> userTaskOptional = userTaskRepository.findById(userTaskId);
        if(userTaskOptional.isPresent()) {
            UserTask userTask = userTaskOptional.get();
            if(userTask.getTaskStatus().equals(TaskStatus.IN_PROGRESS)){
                userTask.setTaskStatus(TaskStatus.DONE);
                userTaskRepository.save(userTask);
                User user = userTask.getUser();
                user.setCoin(user.getCoin() + userTask.getTask().getCoin());
                userRepository.save(user);
                return "Task verified";
            }
            else if(userTask.getTaskStatus().equals(TaskStatus.DONE)){
                throw new AppException(ErrorCode.TASK_ALREADY_DONE);
            }
            else if(userTask.getTaskStatus().equals(TaskStatus.EXPIRED)){
                throw new AppException(ErrorCode.TASK_EXPIRED);
            }
            else throw new AppException(ErrorCode.TASK_NOT_IN_PROGRESS);
        } else {
            throw new AppException(ErrorCode.TASK_NOT_EXISTED);
        }
    }
}
