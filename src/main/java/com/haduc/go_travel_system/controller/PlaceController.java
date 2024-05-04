package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreatePlaceRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.PlaceResponse;
import com.haduc.go_travel_system.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlaceController {
    private final PlaceService placeService;
    @PostMapping("/create")
    public ApiResponse<PlaceResponse> createPlace(@RequestPart CreatePlaceRequest request) throws IOException {
        PlaceResponse placeResponse = placeService.createPlace(request);
        return ApiResponse.<PlaceResponse>builder()
                .code(200)
                .message("Place created successfully")
                .data(placeResponse)
                .build();
    }
}
