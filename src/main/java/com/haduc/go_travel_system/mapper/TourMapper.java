package com.haduc.go_travel_system.mapper;

import com.haduc.go_travel_system.dto.response.TourResponse;
import com.haduc.go_travel_system.entity.Tour;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TourMapper {
    Tour toEntity(TourResponse tourResponse);

    TourResponse toDto(Tour tour);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tour partialUpdate(TourResponse tourResponse, @MappingTarget Tour tour);
}
