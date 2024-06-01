package com.haduc.go_travel_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduleDetailRequest {
    private Long tourScheduleId;
    private String timeLine;
    private String place;
    private String activity;
}
