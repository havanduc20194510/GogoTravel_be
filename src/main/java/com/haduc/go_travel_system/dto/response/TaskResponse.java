package com.haduc.go_travel_system.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.haduc.go_travel_system.entity.TaskType;
import lombok.*;

import java.util.Date;
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
    private Date deadline;
    private String status;
    private TaskType taskType;
}
