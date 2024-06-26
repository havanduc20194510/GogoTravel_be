package com.haduc.go_travel_system.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.haduc.go_travel_system.enums.BookingStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponse {
    private String id;
    private TourResponse tour;
    private UserResponse user;
    private String email;
    private String phone;
    private LocalDate startDate;
    private int numberOfAdults;
    private int numberOfChildren;
    private int numberOfBabies;
    private String note;
    private LocalDateTime bookingDate;
    private Double total;
    private BookingStatus status;

}
