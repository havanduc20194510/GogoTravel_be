package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateScheduleDetailRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.ScheduleDetailResponse;
import com.haduc.go_travel_system.service.ScheduleDetailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/schedule-detail")
public class ScheduleDetailController {
    private ScheduleDetailService scheduleDetailService;

    @PostMapping("/create")
    public ApiResponse<ScheduleDetailResponse> createScheduleDetail(@RequestBody CreateScheduleDetailRequest request) {
        ScheduleDetailResponse scheduleDetailResponse = scheduleDetailService.createScheduleDetail(request);
        return ApiResponse.<ScheduleDetailResponse>builder().data(scheduleDetailResponse).message("create schedule detail successfully!").build();
    }
}
