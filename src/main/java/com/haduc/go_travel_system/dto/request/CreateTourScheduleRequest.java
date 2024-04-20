package com.haduc.go_travel_system.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTourScheduleRequest {
    private String timeInDays;
    private String title;
    private String tourId;
}
