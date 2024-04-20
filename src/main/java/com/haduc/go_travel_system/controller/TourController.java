package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateTourRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.TourResponse;
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
public class TourController {
    private TourService tourService;
    @PostMapping("/create")
    public ApiResponse<TourResponse> createTour(@RequestPart("request")CreateTourRequest request, @RequestPart("images") MultipartFile[] images, @RequestPart("tourType") String tourType ) {
        TourResponse tourResponse = tourService.createTour(request,tourType,images);
        return ApiResponse.<TourResponse>builder().data(tourResponse).message("create tour successfully!").build();
    }

    @GetMapping("/list")
    public ApiResponse<List<TourResponse>> getListTour() {
        List<TourResponse> tourResponses = tourService.getAllTours();
        return ApiResponse.<List<TourResponse>>builder().data(tourResponses).message("get list tour successfully!").build();
    }

}
