package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.CreateScheduleDetailRequest;
import com.haduc.go_travel_system.dto.response.ScheduleDetailResponse;
import com.haduc.go_travel_system.entity.ScheduleDetail;
import com.haduc.go_travel_system.entity.TourSchedule;
import com.haduc.go_travel_system.mapper.ScheduleDetailMapper;
import com.haduc.go_travel_system.repository.ScheduleDetailRepository;
import com.haduc.go_travel_system.repository.TourScheduleRepository;
import com.haduc.go_travel_system.service.ScheduleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleDetailServiceImpl implements ScheduleDetailService {
    private final ScheduleDetailMapper scheduleDetailMapper;
    private final TourScheduleRepository tourScheduleRepository;
    private final ScheduleDetailRepository scheduleDetailRepository;

    @Override
    public ScheduleDetailResponse createScheduleDetail(CreateScheduleDetailRequest request) {
        ScheduleDetail scheduleDetail = scheduleDetailMapper.toScheduleDetail(request);
        TourSchedule tourSchedule = tourScheduleRepository.findById(request.getTourScheduleId()).orElseThrow(()-> new RuntimeException("Tour schedule not found"));
        scheduleDetail.setTourSchedule(tourSchedule);
        ScheduleDetail scheduleDetailSaved = scheduleDetailRepository.save(scheduleDetail);
        return scheduleDetailMapper.toDto(scheduleDetailSaved);
    }
}
