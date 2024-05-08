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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final CloudinaryService cloudinaryService;
    private final CreateTourMapper createTourMapper;
    private final TourTypeRepository tourTypeRepository;
    private final TourImageRepository tourImageRepository;
    private final TourMapper tourMapper;
    @Override
    public CreateTourResponse createTour(CreateTourRequest request) {
        Tour tour = createTourMapper.toTour(request);
        tour.setTotalView(0L);
        tour.setRating(0D);
        TourType type = tourTypeRepository.findByName(request.getTourTypeName());
        if(type == null) {
            TourType newType = new TourType();
            newType.setName(request.getTourTypeName());
            TourType typeSaved = tourTypeRepository.save(newType);
            tour.setTourType(typeSaved);
        }else {
            tour.setTourType(type);
        }
        Tour savedTour = tourRepository.save(tour);
        return createTourMapper.toDto(savedTour);
    }

    @Override
    public TourResponse increaseView(String tourId) {
        Optional<Tour> tour = tourRepository.findById(tourId);
        if(tour.isEmpty()) {
            throw new RuntimeException("Tour not found");
        }
        tour.get().setTotalView(tour.get().getTotalView() + 1);
        Tour updatedTour = tourRepository.save(tour.get());
        return tourMapper.toDto(updatedTour);
    }

    @Override
    public String deleteTour(String tourId) {
        Optional<Tour> tour = tourRepository.findById(tourId);
        if(tour.isEmpty())  {
            throw new RuntimeException("Tour not found");
        }
        tourRepository.delete(tour.get());
        return "Delete tour successfully!";
    }

    @Override
    public Page<TourResponse> findToursWithPagination(int offset, int pageSize) {
        Page<Tour> tours = tourRepository.findAll(PageRequest.of(offset - 1, pageSize));
        if(tours.isEmpty()) {
            throw new RuntimeException("Tours is empty!");
        }
        return tours.map(tourMapper::toDto);
    }

    @Override
    public Page<TourResponse> findToursWithPaginationAndSort(int offset, int pageSize, String sortField) {
        Page<Tour> tours = tourRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(sortField)));
        if(tours.isEmpty()) {
            throw new RuntimeException("Tours is empty!");
        }
        return tours.map(tourMapper::toDto);
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
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new RuntimeException("Tour not found"));
        tour.setName(request.getName());
        tour.setAdultPrice(request.getAdultPrice());
        tour.setChildPrice(request.getChildPrice());
        tour.setBabyPrice(request.getBabyPrice());
        tour.setUnit(request.getUnit());
        tour.setDescription(request.getDescription());
        tour.setNumberOfDays(request.getNumberOfDays());
        tour.setNumberOfNights(request.getNumberOfNights());
        tour.setVehicle(request.getVehicle());
        tour.setDepartureLocation(request.getDepartureLocation());
        tour.setHotelStar(request.getHotelStar());
        tour.setNumberOfSeats(request.getNumberOfSeats());
        tour.setAvailableSeats(request.getAvailableSeats());
        tour.setStatus(request.getStatus());
        tour.setNote(request.getNote());
        TourType type = tourTypeRepository.findByName(request.getTourTypeName());
        if(type == null) {
            TourType newType = new TourType();
            newType.setName(request.getTourTypeName());
            TourType typeSaved = tourTypeRepository.save(newType);
            tour.setTourType(typeSaved);
        }else {
            tour.setTourType(type);
        }
        Tour updatedTour = tourRepository.save(tour);
        return tourMapper.toDto(updatedTour);
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
        return tours.stream().map(tourMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TourResponse> searchTour(String destination, String departureLocation, LocalDate startDate, Long numberOfDay) {
        List<Tour> tours = tourRepository.findByNameContainsIgnoreCaseAndDepartureLocationContainingIgnoreCaseAndDepartureTimesStartDateGreaterThanAndNumberOfDaysLessThanEqual(destination, departureLocation, startDate, numberOfDay);
        if(tours.isEmpty()) {
            throw new RuntimeException("Tour not found");
        }
        return tours.stream().map(tourMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<TourResponse> searchTourWithPagination(String destination, String departureLocation, LocalDate startDate, Long numberOfDay, int offset, int pageSize) {
        Page<Tour> tours = tourRepository.findByNameContainsIgnoreCaseAndDepartureLocationContainingIgnoreCaseAndDepartureTimesStartDateGreaterThanAndNumberOfDaysLessThanEqual(destination, departureLocation, startDate, numberOfDay, PageRequest.of(offset - 1, pageSize));
        if(tours.isEmpty()) {
            throw new RuntimeException("Tour not found");
        }
        return tours.map(tourMapper::toDto);
    }
}
