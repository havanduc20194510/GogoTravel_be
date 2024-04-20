package com.haduc.go_travel_system.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ScheduleDetailResponse {
    private Long id;
    private String timeLine;
    private String place;
    private String description;
    private String activity;
    private String image;
    private TaskResponse task;
}
