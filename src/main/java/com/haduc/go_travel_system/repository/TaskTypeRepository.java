package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
    TaskType findByName(String taskType);
}
