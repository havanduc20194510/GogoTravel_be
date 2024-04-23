package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.CreateTourScheduleRequest;
import com.haduc.go_travel_system.dto.response.TourScheduleResponse;
import com.haduc.go_travel_system.entity.Tour;
import com.haduc.go_travel_system.entity.TourSchedule;
import com.haduc.go_travel_system.enums.ErrorCode;
import com.haduc.go_travel_system.exception.AppException;
import com.haduc.go_travel_system.mapper.TourScheduleMapper;
import com.haduc.go_travel_system.repository.TourRepository;
import com.haduc.go_travel_system.repository.TourScheduleRepository;
import com.haduc.go_travel_system.service.TourScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TourScheduleServiceImpl implements TourScheduleService {
    private TourScheduleMapper tourScheduleMapper;

    private TourScheduleRepository tourScheduleRepository;
    private final TourRepository tourRepository;

    @Override
    public TourScheduleResponse createTourSchedule(CreateTourScheduleRequest request) {
        TourSchedule tourSchedule = tourScheduleMapper.toSchedule(request);
        Tour tour = tourRepository.findById(request.getTourId()).orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_FOUND));
        tourSchedule.setTour(tour);
        TourSchedule tourScheduleSaved = tourScheduleRepository.save(tourSchedule);
        return tourScheduleMapper.toDto(tourScheduleSaved);
    }

    @Override
    public TourScheduleResponse updateTourSchedule(CreateTourScheduleRequest request) {
        return null;
    }

    @Override
    public String deleteTourSchedule(Long id) {
        return null;
    }

    @Override
    public List<TourScheduleResponse> getTourSchedule(Long tourId) {
        return null;
    }
}
