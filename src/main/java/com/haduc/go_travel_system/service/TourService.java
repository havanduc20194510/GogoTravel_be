package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.CreateTourRequest;
import com.haduc.go_travel_system.dto.response.CreateTourResponse;
import com.haduc.go_travel_system.dto.response.TourResponse;
import com.haduc.go_travel_system.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface TourService {
    CreateTourResponse createTour(CreateTourRequest request);

    TourResponse uploadImage(MultipartFile[] file, String tourId);

    TourResponse getTour(String tourId);

    TourResponse increaseView(String tourId);

    TourResponse updateTour(CreateTourRequest request, String tourId);

    String deleteTour(String tourId);


    List<TourResponse> getAllTours();

    Page<TourResponse> findToursWithPagination(int offset, int pageSize);

    Page<TourResponse> findToursWithPaginationAndSort(int offset, int pageSize, String sortField);

    List<TourResponse> searchTour(String destination, String departureLocation, LocalDate startDate, Long numberOfDay);

    Page<TourResponse> searchTourWithPagination(String destination, String departureLocation, LocalDate startDate, Long numberOfDay, int offset, int pageSize);

    Page<TourResponse> searchTourWithPaginationAndSort(String destination, String departureLocation, LocalDate startDate, Long numberOfDay,String sortField, int offset, int pageSize);

    Page<TourResponse> searchTourWithPaginationAndSortAndFilter(String destination, String departureLocation, LocalDate startDate, Long numberOfDay,String filterType, Double filterPriceMin, Double filterPriceMax,String sortField, int offset, int pageSize);
}
