package com.haduc.go_travel_system.dto.request;

import com.haduc.go_travel_system.entity.TaskType;
import com.haduc.go_travel_system.enums.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTaskRequest {
    private Long tourScheduleId;
    private String name;
    private String description;
    private Long coin;
    private String reward;
    private LocalDate deadline;
    private String taskTypeName;
}
