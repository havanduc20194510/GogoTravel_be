package com.haduc.go_travel_system.mapper;

import com.haduc.go_travel_system.dto.request.CreateScheduleDetailRequest;
import com.haduc.go_travel_system.dto.response.ScheduleDetailResponse;
import com.haduc.go_travel_system.entity.ScheduleDetail;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScheduleDetailMapper {
    ScheduleDetail toEntity(ScheduleDetailResponse scheduleDetailResponse);

    ScheduleDetailResponse toDto(ScheduleDetail scheduleDetail);

    ScheduleDetail toScheduleDetail(CreateScheduleDetailRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScheduleDetail partialUpdate(ScheduleDetailResponse scheduleDetailResponse, @MappingTarget ScheduleDetail scheduleDetail);
}
