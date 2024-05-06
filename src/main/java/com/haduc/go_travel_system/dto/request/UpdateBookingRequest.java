package com.haduc.go_travel_system.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBookingRequest {
    @Email(message = "Email should be valid")
    private String email;
    private String phone;
    private LocalDate startDate;
    private int numberOfAdults;
    private int numberOfChildren;
    private int numberOfBabies;
    private String note;
}
