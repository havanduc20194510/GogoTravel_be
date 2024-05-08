package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateDepartureTimeRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.DepartureTimeResponse;
import com.haduc.go_travel_system.service.DepartureTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departure-time")
@CrossOrigin(origins = "*")
public class DepartureTimeController {
    private final DepartureTimeService departureTimeService;

    @PostMapping("/create")
    public ApiResponse<DepartureTimeResponse> createDepartureTime(@RequestBody CreateDepartureTimeRequest request) {
        DepartureTimeResponse departureTimeResponse = departureTimeService.createDepartureTime(request);
        return ApiResponse.<DepartureTimeResponse>builder()
                .message("Departure time created successfully")
                .data(departureTimeResponse)
                .build();
    }

    @GetMapping("/get/{departureTimeId}")
    public ApiResponse<DepartureTimeResponse> getDepartureTime(@PathVariable Long departureTimeId) {
        DepartureTimeResponse departureTimeResponse = departureTimeService.getDepartureTime(departureTimeId);
        return ApiResponse.<DepartureTimeResponse>builder()
                .message("Departure time retrieved successfully")
                .data(departureTimeResponse)
                .build();
    }

    @PutMapping("/update/{departureTimeId}")
    public ApiResponse<DepartureTimeResponse> updateDepartureTime(@RequestBody CreateDepartureTimeRequest request, @PathVariable Long departureTimeId) {
        DepartureTimeResponse departureTimeResponse = departureTimeService.updateDepartureTime(request, departureTimeId);
        return ApiResponse.<DepartureTimeResponse>builder()
                .message("Departure time updated successfully")
                .data(departureTimeResponse)
                .build();
    }

    @DeleteMapping("/delete/{departureTimeId}")
    public ApiResponse<String> deleteDepartureTime(@PathVariable Long departureTimeId) {
        String message = departureTimeService.deleteDepartureTime(departureTimeId);
        return ApiResponse.<String>builder()
                .message("Departure time deleted successfully")
                .data(message)
                .build();
    }


}
