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
}
