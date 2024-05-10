package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.CreatePlaceRequest;
import com.haduc.go_travel_system.dto.response.PlaceResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PlaceService {
    PlaceResponse createPlace(CreatePlaceRequest request) throws IOException;
    List<PlaceResponse> getAllPlaces();
    PlaceResponse updatePlace(Long placeId, CreatePlaceRequest request) throws IOException;
    PlaceResponse uploadImage(Long placeId, MultipartFile file) throws IOException;
    String deletePlace(Long placeId);
    PlaceResponse getPlace(Long placeId);
    List<PlaceResponse> getPlaceByName(String placeName);
    List<PlaceResponse> getPlaceByAddress(String address);
    List<PlaceResponse> searchPlace(String name, String address, String activities);
    Page<PlaceResponse> searchPlace(String name, String address, String activities, int offset, int pageSize);

}
