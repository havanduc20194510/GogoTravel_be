package com.haduc.go_travel_system.dto.request;

import com.haduc.go_travel_system.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTaskRequest {
    private String userId;
    private String email;
    private String phone;
    private String tourId;
    private String bookingTourId;
    private Long taskId;
    private LocalDate taskDeadline;
    private TaskStatus taskStatus;
}
