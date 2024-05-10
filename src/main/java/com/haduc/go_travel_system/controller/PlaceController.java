package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreatePlaceRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.PlaceResponse;
import com.haduc.go_travel_system.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlaceController {
    private final PlaceService placeService;
    @PostMapping("/create")
    public ApiResponse<PlaceResponse> createPlace(@RequestBody CreatePlaceRequest request) throws IOException {
        PlaceResponse placeResponse = placeService.createPlace(request);
        return ApiResponse.<PlaceResponse>builder()
                .code(200)
                .message("Place created successfully")
                .data(placeResponse)
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<PlaceResponse> updatePlace(@PathVariable Long id, @RequestPart CreatePlaceRequest request) throws IOException {
        PlaceResponse placeResponse = placeService.updatePlace(id, request);
        return ApiResponse.<PlaceResponse>builder()
                .code(200)
                .message("Place updated successfully")
                .data(placeResponse)
                .build();
    }

    @PostMapping("/upload-image/{id}")
    public ApiResponse<PlaceResponse> uploadImage(@PathVariable Long id, @RequestPart("image") MultipartFile image) throws IOException {
        PlaceResponse placeResponse = placeService.uploadImage(id, image);
        return ApiResponse.<PlaceResponse>builder()
                .code(200)
                .message("Image uploaded successfully")
                .data(placeResponse)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deletePlace(@PathVariable Long id) {
        String message = placeService.deletePlace(id);
        return ApiResponse.<String>builder()
                .code(200)
                .message(message)
                .data("Delete place successfully")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<PlaceResponse> getPlace(@PathVariable Long id) {
        PlaceResponse placeResponse = placeService.getPlace(id);
        return ApiResponse.<PlaceResponse>builder()
                .code(200)
                .message("Get place successfully")
                .data(placeResponse)
                .build();
    }

    @GetMapping("/name/{name}")
    public ApiResponse<List<PlaceResponse>> getPlaceByName(@PathVariable String name) {
        List<PlaceResponse> placeResponses = placeService.getPlaceByName(name);
        return ApiResponse.<List<PlaceResponse>>builder()
                .code(200)
                .message("Get place by name successfully")
                .data(placeResponses)
                .build();
    }

    @GetMapping("/address/{address}")
    public ApiResponse<List<PlaceResponse>> getPlaceByAddress(@PathVariable String address) {
        List<PlaceResponse> placeResponses = placeService.getPlaceByAddress(address);
        return ApiResponse.<List<PlaceResponse>>builder()
                .code(200)
                .message("Get place by address successfully")
                .data(placeResponses)
                .build();
    }

    @GetMapping("/all")
    public ApiResponse<List<PlaceResponse>> getAllPlaces() {
        List<PlaceResponse> placeResponses = placeService.getAllPlaces();
        return ApiResponse.<List<PlaceResponse>>builder()
                .code(200)
                .message("Get all places successfully")
                .data(placeResponses)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<PlaceResponse>> searchPlace(@RequestParam String name, @RequestParam String address, @RequestParam String activities) {
        List<PlaceResponse> placeResponses = placeService.searchPlace(name, address, activities);
        return ApiResponse.<List<PlaceResponse>>builder()
                .code(200)
                .message("Search place successfully")
                .data(placeResponses)
                .build();
    }

    @GetMapping("/search/pagination")
    public ApiResponse<Page<PlaceResponse>> searchPlace(@RequestParam String name, @RequestParam String address, @RequestParam String activities, @RequestParam(defaultValue = "1") int offset, @RequestParam(defaultValue = "5") int pageSize) {
        Page<PlaceResponse> placeResponses = placeService.searchPlace(name, address, activities,offset,pageSize);
        return ApiResponse.<Page<PlaceResponse>>builder()
                .code(200)
                .message("Search place successfully")
                .data(placeResponses)
                .build();
    }


}
