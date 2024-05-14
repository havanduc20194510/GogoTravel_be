package com.haduc.go_travel_system.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartureTimeResponse {
    private Long id;
    private LocalDate startDate;
    private Long numberOfSeats;
    private Long bookedSeats;
    private boolean available;
}
