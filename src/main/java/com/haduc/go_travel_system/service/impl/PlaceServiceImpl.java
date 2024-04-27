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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private PlaceMapper placeMapper;
    private ActivityRepository activityRepository;

    private PlaceRepository placeRepository;

    private CloudinaryService cloudinaryService;

    @Override
    public PlaceResponse createPlace(CreatePlaceRequest request, MultipartFile image) throws IOException {
        Map uploadResult = cloudinaryService.uploadImage(image);
        Place place = placeMapper.toPlace(request);
        place.setImage(uploadResult.get("url").toString());
        Optional<Activity> activity = activityRepository.findById(request.getActivityId());
        if (activity.isPresent()) {
            place.setActivity(activity.get());
            Place placeSaved = placeRepository.save(place);
            return placeMapper.toDto(placeSaved);
        }else {
            throw new RuntimeException("Activity not found");
        }

    }
}
