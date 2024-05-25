package com.haduc.go_travel_system.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRequest {
    private String tourId;
    @Email(message = "EMAIL_INVALID")
    private String email;
    private String phone;
    private LocalDate startDate;
    private int numberOfAdults;
    private int numberOfChildren;
    private int numberOfBabies;
    private String note;
}
