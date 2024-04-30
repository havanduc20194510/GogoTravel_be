package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.CreateDepartureTimeRequest;
import com.haduc.go_travel_system.dto.response.DepartureTimeResponse;
import com.haduc.go_travel_system.entity.DepartureTime;
import com.haduc.go_travel_system.entity.Tour;
import com.haduc.go_travel_system.mapper.DepartureTimeMapper;
import com.haduc.go_travel_system.repository.DepartureTimeRepository;
import com.haduc.go_travel_system.repository.TourRepository;
import com.haduc.go_travel_system.service.DepartureTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Optional<Tour> tour = tourRepository.findById(request.getTourId());
        if (tour.isPresent()) {
            departureTime.setTour(tour.get());
            DepartureTime savedDepartureTime = departureTimeRepository.save(departureTime);
            return departureTimeMapper.toDto(savedDepartureTime);
        }else {
            throw new RuntimeException("Tour not found");
        }
    }
}
