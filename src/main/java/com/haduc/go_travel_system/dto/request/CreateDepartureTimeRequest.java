package com.haduc.go_travel_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDepartureTimeRequest {
    private String tourId;
    private LocalDate startDate;
}
