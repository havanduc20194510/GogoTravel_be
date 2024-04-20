package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.CreateTourRequest;
import com.haduc.go_travel_system.dto.response.TourResponse;
import com.haduc.go_travel_system.entity.Tour;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TourService {
    TourResponse createTour(CreateTourRequest request, String tourType, MultipartFile[] images);
    TourResponse updateTour(Tour tour);
    TourResponse getTour(Long id);

    List<TourResponse> getAllTours();
}
