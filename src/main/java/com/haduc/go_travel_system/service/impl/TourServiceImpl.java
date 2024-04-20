package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.CreateTourRequest;
import com.haduc.go_travel_system.dto.response.TourImageReponse;
import com.haduc.go_travel_system.dto.response.TourResponse;
import com.haduc.go_travel_system.entity.Tour;
import com.haduc.go_travel_system.entity.TourImage;
import com.haduc.go_travel_system.entity.TourType;
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

@Service
@AllArgsConstructor
public class TourServiceImpl implements TourService {
    private TourRepository tourRepository;
    private CloudinaryService cloudinaryService;
    private TourMapper tourMapper;
    private TourTypeRepository tourTypeRepository;

    private TourImageRepository tourImageRepository;

    @Override
    public TourResponse createTour(CreateTourRequest request, String tourType, MultipartFile[] images) {
        Tour tour = tourMapper.toTour(request);
        TourType type = tourTypeRepository.findByName(tourType);
        if(type == null) {
            TourType newType = new TourType();
            newType.setName(tourType);
            TourType typeSaved = tourTypeRepository.save(newType);
            tour.setTourType(typeSaved);
        }else {
            tour.setTourType(type);
        }
        Tour savedTour = tourRepository.save(tour);
        TourResponse tourResponse = tourMapper.toDto(savedTour);
        Arrays.stream(images).forEach(image -> {
            try {
                String imageUrl = cloudinaryService.uploadImage(image).get("url").toString();
                TourImage tourImage = new TourImage();
                tourImage.setTour(tour);
                tourImage.setUrl(imageUrl);
                tourImageRepository.save(tourImage);
            } catch (IOException e) {
                throw new RuntimeException("Error uploading image");
            }
        });
        List<TourImage> tourImages = tourImageRepository.findByTourTourId(tour.getTourId());
        List<TourImageReponse> imageOfTour = tourImages.stream().map(tourImage -> TourImageReponse.builder().id(tourImage.getId()).url(tourImage.getUrl()).build()).toList();
        tourResponse.setImages(imageOfTour);
        return tourResponse;
    }

    @Override
    public TourResponse updateTour(Tour tour) {
        return null;
    }

    @Override
    public TourResponse getTour(Long id) {
        return null;
    }

    @Override
    public List<TourResponse> getAllTours() {
        List<Tour> tours = tourRepository.findAll();
        List<TourResponse> tourResponses = tours.stream().map(tourMapper::toDto).toList();
        return tourResponses;
    }
}
