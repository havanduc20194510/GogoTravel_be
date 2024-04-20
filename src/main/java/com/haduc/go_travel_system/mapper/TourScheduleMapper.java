package com.haduc.go_travel_system.mapper;

import com.haduc.go_travel_system.dto.request.CreateTourScheduleRequest;
import com.haduc.go_travel_system.dto.response.TourScheduleResponse;
import com.haduc.go_travel_system.entity.TourSchedule;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TourScheduleMapper {
    TourScheduleResponse toDto(TourSchedule tourSchedule);

    TourSchedule toTourSchedule(CreateTourScheduleRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TourSchedule partialUpdate(TourScheduleResponse tourScheduleResponse, @MappingTarget TourSchedule tourSchedule);
}
