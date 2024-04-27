package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.CreatePlaceRequest;
import com.haduc.go_travel_system.dto.response.PlaceResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PlaceService {
    PlaceResponse createPlace(CreatePlaceRequest request, MultipartFile image) throws IOException;
}
