package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.CreateTourRequest;
import com.haduc.go_travel_system.dto.response.CreateTourResponse;
import com.haduc.go_travel_system.dto.response.TourImageResponse;
import com.haduc.go_travel_system.dto.response.TourResponse;
import com.haduc.go_travel_system.entity.Tour;
import com.haduc.go_travel_system.entity.TourImage;
import com.haduc.go_travel_system.entity.TourType;
import com.haduc.go_travel_system.enums.ErrorCode;
import com.haduc.go_travel_system.exception.AppException;
import com.haduc.go_travel_system.mapper.CreateTourMapper;
import com.haduc.go_travel_system.mapper.TourMapper;
import com.haduc.go_travel_system.repository.BookingTourRepository;
import com.haduc.go_travel_system.repository.TourImageRepository;
import com.haduc.go_travel_system.repository.TourRepository;
import com.haduc.go_travel_system.repository.TourTypeRepository;
import com.haduc.go_travel_system.service.CloudinaryService;
import com.haduc.go_travel_system.service.TourService;
import com.haduc.go_travel_system.util.TourSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    private final BookingTourRepository bookingTourRepository;

    @Override
    public CreateTourResponse createTour(CreateTourRequest request) {
        Tour tour = createTourMapper.toTour(request);
        tour.setTotalView(0L);
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
            throw new AppException(ErrorCode.TOUR_NOT_FOUND);
        }
        tour.get().setTotalView(tour.get().getTotalView() + 1);
        Tour updatedTour = tourRepository.save(tour.get());
        return tourMapper.toDto(updatedTour);
    }

    @Override
    public String deleteTour(String tourId) {
        Optional<Tour> tour = tourRepository.findById(tourId);
        if(tour.isEmpty())  {
            throw new AppException(ErrorCode.TOUR_NOT_FOUND);
        }
        // check tourId in booking
        bookingTourRepository.findByTourTourId(tourId);
        if (!bookingTourRepository.findByTourTourId(tourId).isEmpty()) {
            throw new AppException(ErrorCode.TOUR_IN_BOOKING_CANNOT_DELETE);
        }

        tourRepository.delete(tour.get());
        return "Delete tour successfully!";
    }

    @Override
    public Page<TourResponse> findToursWithPagination(int offset, int pageSize) {
        Page<Tour> tours = tourRepository.findAll(PageRequest.of(offset - 1, pageSize));
        return tours.map(tourMapper::toDto);
    }

    @Override
    public Page<TourResponse> findToursWithPaginationAndSort(int offset, int pageSize, String sortField) {
        Page<Tour> tours = tourRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(sortField)));
        return tours.map(tourMapper::toDto);
    }

    @Override
    public TourResponse uploadImage(MultipartFile[] images, String tourId) {
        Optional<Tour> tour = tourRepository.findById(tourId);
        if(tour.isEmpty()) {
            throw new AppException(ErrorCode.TOUR_NOT_FOUND);
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
                throw new AppException(ErrorCode.UPLOAD_FILE_FAILED);
            }
        });
        List<TourImage> tourImages = tourImageRepository.findByTourTourId(tourId);
        List<TourImageResponse> imageOfTour = tourImages.stream().map(tourImage -> TourImageResponse.builder().id(tourImage.getId()).url(tourImage.getUrl()).build()).toList();
        tourResponse.setImages(imageOfTour);
        return tourResponse;
    }

    @Override
    public TourResponse updateTour(CreateTourRequest request, String tourId) {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_FOUND));
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
            throw new AppException(ErrorCode.TOUR_NOT_FOUND);
        }
        return tourMapper.toDto(tour.get());
    }

    @Override
    public List<TourResponse> getAllTours() {
        List<Tour> tours = tourRepository.findAll();
        return tours.stream().map(tourMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TourResponse> topTourRecommend() {
        List<Tour> tours = tourRepository.findTop5ByOrderByTotalViewDesc();
        return tours.stream().map(tourMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<TourResponse> searchTourWithPaginationAndSortAndFilter(String destination, String departureLocation, LocalDate startDate, Long numberOfDay, String filterType, Double filterPriceMin, Double filterPriceMax, String sortField, int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset-1, pageSize, Sort.by(sortField));
        Specification<Tour> spec = TourSpecification.searchTours(destination, departureLocation, startDate, numberOfDay, filterPriceMin, filterPriceMax, filterType);
        Page<Tour> tours = tourRepository.findAll(spec, pageable);
        return tours.map(tourMapper::toDto);
    }
}
