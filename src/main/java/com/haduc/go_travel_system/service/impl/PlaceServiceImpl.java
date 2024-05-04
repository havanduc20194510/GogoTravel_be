package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.CreatePlaceRequest;
import com.haduc.go_travel_system.dto.response.PlaceResponse;
import com.haduc.go_travel_system.entity.Activity;
import com.haduc.go_travel_system.entity.Place;
import com.haduc.go_travel_system.mapper.PlaceMapper;
import com.haduc.go_travel_system.repository.ActivityRepository;
import com.haduc.go_travel_system.repository.PlaceRepository;
import com.haduc.go_travel_system.service.CloudinaryService;
import com.haduc.go_travel_system.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceMapper placeMapper;
    private final ActivityRepository activityRepository;

    private final PlaceRepository placeRepository;

    private final CloudinaryService cloudinaryService;

    @Override
    public PlaceResponse createPlace(CreatePlaceRequest request) {
        Place place = placeMapper.toPlace(request);
        Optional<Activity> activity = activityRepository.findById(request.getActivityId());
        if (activity.isPresent()) {
            place.setActivity(activity.get());
            Place placeSaved = placeRepository.save(place);
            return placeMapper.toDto(placeSaved);
        }else {
            throw new RuntimeException("Activity not found");
        }
    }

    @Override
    public PlaceResponse updatePlace(Long placeId, CreatePlaceRequest request) {
        return null;
    }

    @Override
    public PlaceResponse uploadImage(Long placeId, MultipartFile file) throws IOException {
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new RuntimeException("Place not found"));
        Map uploadResult = cloudinaryService.uploadImage(file);
        place.setImage(uploadResult.get("url").toString());
        Place placeSaved = placeRepository.save(place);
        return placeMapper.toDto(placeSaved);
    }

    @Override
    public String deletePlace(Long placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new RuntimeException("Place not found"));
        placeRepository.delete(place);
        return "Place deleted successfully";
    }

    @Override
    public PlaceResponse getPlace(Long placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new RuntimeException("Place not found"));
        return placeMapper.toDto(place);
    }

    @Override
    public List<PlaceResponse> getPlaceByName(String placeName) {
        List<Place> places = placeRepository.findByName(placeName);
        if(places.isEmpty()) {
            throw new RuntimeException("Place not found");
        }
        return places.stream().map(placeMapper::toDto).toList();
    }

    @Override
    public List<PlaceResponse> getPlaceByCity(String location) {
        List<Place> places = placeRepository.findByLocation(location);
        return places.stream().map(placeMapper::toDto).toList();
    }

}
