package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateTourRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.CreateTourResponse;
import com.haduc.go_travel_system.dto.response.TourResponse;
import com.haduc.go_travel_system.entity.Tour;
import com.haduc.go_travel_system.service.TourService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Tour Resource",
        description = "CRUD REST APIs - Create Tour, Update Tour, Get Tour, Get list Tours, Delete Tour"
)
@RestController
@AllArgsConstructor
@RequestMapping("/tour")
@CrossOrigin(origins = "*")
public class TourController {
    private TourService tourService;
    @PostMapping("/create")
    public ApiResponse<CreateTourResponse> createTour(@RequestBody CreateTourRequest request) {
        CreateTourResponse createTourResponse = tourService.createTour(request);
        return ApiResponse.<CreateTourResponse>builder().data(createTourResponse).message("create tour successfully!").build();
    }

    @PostMapping("/upload-image")
    public ApiResponse<TourResponse> uploadImage(@RequestPart("images") MultipartFile[] images, @RequestPart("tourId") String tourId) {
        TourResponse tourResponse = tourService.uploadImage(images, tourId);
        return ApiResponse.<TourResponse>builder().data(tourResponse).message("upload image successfully!").build();
    }

    @GetMapping("/list")
    public ApiResponse<List<TourResponse>> getListTour() {
        List<TourResponse> tours = tourService.getAllTours();
        return ApiResponse.<List<TourResponse>>builder().data(tours).message("get list tour successfully!").build();
    }

    @GetMapping("/{tourId}")
    public ApiResponse<TourResponse> getTour(@PathVariable String tourId) {
        TourResponse tour = tourService.getTour(tourId);
        return ApiResponse.<TourResponse>builder().data(tour).message("get tour successfully!").build();
    }
}
