package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.CreateTourRequest;
import com.haduc.go_travel_system.dto.response.CreateTourResponse;
import com.haduc.go_travel_system.dto.response.TourImageReponse;
import com.haduc.go_travel_system.dto.response.TourResponse;
import com.haduc.go_travel_system.entity.Tour;
import com.haduc.go_travel_system.entity.TourImage;
import com.haduc.go_travel_system.entity.TourType;
import com.haduc.go_travel_system.mapper.CreateTourMapper;
import com.haduc.go_travel_system.mapper.TourMapper;
import com.haduc.go_travel_system.repository.TourImageRepository;
import com.haduc.go_travel_system.repository.TourRepository;
import com.haduc.go_travel_system.repository.TourTypeRepository;
import com.haduc.go_travel_system.service.CloudinaryService;
import com.haduc.go_travel_system.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourServiceImpl implements TourService {
    private TourRepository tourRepository;
    private CloudinaryService cloudinaryService;
    private CreateTourMapper createTourMapper;
    private TourTypeRepository tourTypeRepository;
    private TourImageRepository tourImageRepository;

    private TourMapper tourMapper;

    @Override
    public CreateTourResponse createTour(CreateTourRequest request) {
        Tour tour = createTourMapper.toTour(request);
        TourType type = tourTypeRepository.findByName(request.getTourType());
        if(type == null) {
            TourType newType = new TourType();
            newType.setName(request.getTourType());
            TourType typeSaved = tourTypeRepository.save(newType);
            tour.setTourType(typeSaved);
        }else {
            tour.setTourType(type);
        }
        Tour savedTour = tourRepository.save(tour);
        return createTourMapper.toDto(savedTour);
    }

    @Override
    public TourResponse uploadImage(MultipartFile[] images, String tourId) {
        Optional<Tour> tour = tourRepository.findById(tourId);
        if(tour.isEmpty()) {
            throw new RuntimeException("Tour not found");
        }
        TourResponse tourResponse = tourMapper.toDto(tour.get());
        Arrays.stream(images).forEach(image -> {
            try {
                String imageUrl = cloudinaryService.uploadImage(image).get("url").toString();
                TourImage tourImage = new TourImage();
                tourImage.setTour(tour.get());
                tourImage.setUrl(imageUrl);
                tourImageRepository.save(tourImage);
            } catch (IOException e) {
                throw new RuntimeException("Error uploading image");
            }
        });
        List<TourImage> tourImages = tourImageRepository.findByTourTourId(tourId);
        List<TourImageReponse> imageOfTour = tourImages.stream().map(tourImage -> TourImageReponse.builder().id(tourImage.getId()).url(tourImage.getUrl()).build()).toList();
        tourResponse.setImages(imageOfTour);
        return tourResponse;
    }

    @Override
    public TourResponse updateTour(CreateTourRequest request, String tourId) {
        Tour tour = tourRepository.findByTourId(tourId);
        if(tour == null) {
            throw new RuntimeException("Tour not found");
        }
        return null;
    }

    @Override
    public TourResponse getTour(String tourId) {
        Optional<Tour> tour = tourRepository.findById(tourId);
        if(tour.isEmpty()) {
            throw new RuntimeException("Tour not found");
        }
        return tourMapper.toDto(tour.get());
    }

    @Override
    public List<TourResponse> getAllTours() {
        List<Tour> tours = tourRepository.findAll();
        return tours.stream().map(tour -> tourMapper.toDto(tour)).collect(Collectors.toList());
    }
}
