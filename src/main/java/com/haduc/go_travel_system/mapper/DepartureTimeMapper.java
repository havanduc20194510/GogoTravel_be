package com.haduc.go_travel_system.mapper;

import com.haduc.go_travel_system.dto.request.CreateDepartureTimeRequest;
import com.haduc.go_travel_system.dto.response.DepartureTimeResponse;
import com.haduc.go_travel_system.entity.DepartureTime;
import com.haduc.go_travel_system.util.BooleanYNMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartureTimeMapper {
    DepartureTime toEntity(DepartureTimeResponse departureTimeResponse);
    DepartureTimeResponse toDto(DepartureTime departureTime);

    DepartureTime toDepartureTime(CreateDepartureTimeRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DepartureTime partialUpdate(DepartureTimeResponse departureTimeResponse, @MappingTarget DepartureTime departureTime);
}
