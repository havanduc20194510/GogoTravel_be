package com.haduc.go_travel_system.dto.request;

import com.haduc.go_travel_system.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTaskRequest {
    private Long tourScheduleId;
    private String name;
    private String description;
    private Long coin;
    private String reward;
    private LocalDate deadline;
    private TaskStatus status;
    private String taskTypeName;
}
