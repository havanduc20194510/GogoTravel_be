package com.haduc.go_travel_system.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.haduc.go_travel_system.entity.Task;
import com.haduc.go_travel_system.enums.BookingStatus;
import com.haduc.go_travel_system.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class UserTaskResponse {
    private String id;
    private String userId;
    private String email;
    private String phone;
    private String tourId;
    private String tourName;
    private String bookingTourId;
    private String taskName;
    private String taskDescription;
    private Long coin;
    private String reward;
    private LocalDate taskDeadline;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
}
