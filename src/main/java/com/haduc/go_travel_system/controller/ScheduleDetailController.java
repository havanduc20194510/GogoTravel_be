package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateScheduleDetailRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.ScheduleDetailResponse;
import com.haduc.go_travel_system.service.ScheduleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule-detail")
@CrossOrigin(origins = "*")
public class ScheduleDetailController {
    private final ScheduleDetailService scheduleDetailService;

    @PostMapping("/create")
    public ApiResponse<ScheduleDetailResponse> createScheduleDetail(@RequestBody CreateScheduleDetailRequest request) {
        ScheduleDetailResponse scheduleDetailResponse = scheduleDetailService.createScheduleDetail(request);
        return ApiResponse.<ScheduleDetailResponse>builder().data(scheduleDetailResponse).message("create schedule detail successfully!").build();
    }

    @GetMapping("/get/{scheduleDetailId}")
    public ApiResponse<ScheduleDetailResponse> getScheduleDetail(@PathVariable Long scheduleDetailId) {
        ScheduleDetailResponse scheduleDetailResponse = scheduleDetailService.getScheduleDetail(scheduleDetailId);
        return ApiResponse.<ScheduleDetailResponse>builder().data(scheduleDetailResponse).message("get schedule detail successfully!").build();
    }

    @PutMapping("/update/{scheduleDetailId}")
    public ApiResponse<ScheduleDetailResponse> updateScheduleDetail(@RequestBody CreateScheduleDetailRequest request, @PathVariable Long scheduleDetailId) {
        ScheduleDetailResponse scheduleDetailResponse = scheduleDetailService.updateScheduleDetail(request, scheduleDetailId);
        return ApiResponse.<ScheduleDetailResponse>builder().data(scheduleDetailResponse).message("update schedule detail successfully!").build();
    }

    @DeleteMapping("/delete/{scheduleDetailId}")
    public ApiResponse<String> deleteScheduleDetail(@PathVariable Long scheduleDetailId) {
        String message = scheduleDetailService.deleteScheduleDetail(scheduleDetailId);
        return ApiResponse.<String>builder().data(message).message("delete schedule detail successfully!").build();
    }

}
