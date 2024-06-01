package com.haduc.go_travel_system.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.haduc.go_travel_system.entity.TaskType;
import com.haduc.go_travel_system.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class TaskResponse {
    private Long id;
    private String name;
    private String description;
    private Long coin;
    private String reward;
    private TaskType taskType;
}
