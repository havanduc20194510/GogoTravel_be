package com.haduc.go_travel_system.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class TaskVerifyResponse {
    private String userId;
    private String userName;
    private String email;
    private String phone;
    private String tourId;
    private String tourName;
    private List<TaskResponse> tasks;
    private String taskStatus;
}
