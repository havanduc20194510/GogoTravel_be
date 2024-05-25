package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.CreateDepartureTimeRequest;
import com.haduc.go_travel_system.dto.response.DepartureTimeResponse;
import com.haduc.go_travel_system.entity.DepartureTime;
import com.haduc.go_travel_system.entity.Tour;
import com.haduc.go_travel_system.enums.ErrorCode;
import com.haduc.go_travel_system.exception.AppException;
import com.haduc.go_travel_system.mapper.DepartureTimeMapper;
import com.haduc.go_travel_system.repository.DepartureTimeRepository;
import com.haduc.go_travel_system.repository.TourRepository;
import com.haduc.go_travel_system.service.DepartureTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartureTimeServiceImpl implements DepartureTimeService {
    private final DepartureTimeMapper departureTimeMapper;
    private final TourRepository tourRepository;
    private final DepartureTimeRepository departureTimeRepository;

    @Override
    public DepartureTimeResponse createDepartureTime(CreateDepartureTimeRequest request) {
        DepartureTime departureTime = departureTimeMapper.toDepartureTime(request);
        departureTime.setBookedSeats(0L);
        departureTime.setAvailable(true);
        Optional<Tour> tour = tourRepository.findById(request.getTourId());
        if (tour.isPresent()) {
            departureTime.setTour(tour.get());
            if(departureTimeRepository.existsByTourAndStartDate(departureTime.getTour(), departureTime.getStartDate())){
                throw new AppException(ErrorCode.DEPARTURE_TIME_EXISTED);
            }
            DepartureTime savedDepartureTime = departureTimeRepository.save(departureTime);
            return departureTimeMapper.toDto(savedDepartureTime);
        }else {
            throw new AppException(ErrorCode.TOUR_NOT_FOUND);
        }
    }

    @Override
    public DepartureTimeResponse getDepartureTime(Long departureTimeId) {
        DepartureTime departureTime = departureTimeRepository.findById(departureTimeId).orElseThrow(() -> new AppException(ErrorCode.DEPARTURE_TIME_NOT_FOUND));
        return departureTimeMapper.toDto(departureTime);
    }

    @Override
    public DepartureTimeResponse updateDepartureTime(CreateDepartureTimeRequest request, Long departureTimeId) {
        DepartureTime departureTime = departureTimeRepository.findById(departureTimeId).orElseThrow(() -> new AppException(ErrorCode.DEPARTURE_TIME_NOT_FOUND));
        Optional<Tour> tour = tourRepository.findById(request.getTourId());
        if (tour.isPresent()) {
            departureTime.setTour(tour.get());
            departureTime.setStartDate(request.getStartDate());
            departureTime.setNumberOfSeats(request.getNumberOfSeats());
            DepartureTime savedDepartureTime = departureTimeRepository.save(departureTime);
            return departureTimeMapper.toDto(savedDepartureTime);
        }else {
            throw new AppException(ErrorCode.TOUR_NOT_FOUND);
        }
    }

    @Override
    public String deleteDepartureTime(Long departureTimeId) {
        departureTimeRepository.deleteById(departureTimeId);
        return "Delete successfully";
    }

    @Override
    public List<DepartureTimeResponse> getDepartureTimeByTourId(String tourId) {
        List<DepartureTime> departureTime = departureTimeRepository.findByTourTourId(tourId);
        return departureTime.stream().map(departureTimeMapper::toDto).toList();
    }

    @Override
    public void updateBookedSeats(Long departureTimeId, Long bookedSeats) {
        DepartureTime departureTime = departureTimeRepository.findById(departureTimeId).orElseThrow(() -> new AppException(ErrorCode.DEPARTURE_TIME_NOT_FOUND));
        departureTime.setBookedSeats(bookedSeats);
        departureTimeRepository.save(departureTime);
    }

    @Override
    public void updateAvailable(Long departureTimeId) {
        DepartureTime departureTime = departureTimeRepository.findById(departureTimeId).orElseThrow(() -> new AppException(ErrorCode.DEPARTURE_TIME_NOT_FOUND));
        departureTime.setAvailable(departureTime.getBookedSeats() < departureTime.getNumberOfSeats());
        departureTimeRepository.save(departureTime);
    }
}
