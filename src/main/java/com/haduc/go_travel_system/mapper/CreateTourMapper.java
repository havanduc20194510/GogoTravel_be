package com.haduc.go_travel_system.mapper;

import com.haduc.go_travel_system.dto.request.CreateTourRequest;
import com.haduc.go_travel_system.dto.response.CreateTourResponse;
import com.haduc.go_travel_system.entity.Tour;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateTourMapper {
    Tour toEntity(CreateTourResponse createTourResponse);

    CreateTourResponse toDto(Tour tour);

    Tour toTour(CreateTourRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tour partialUpdate(CreateTourResponse createTourResponse, @MappingTarget Tour tour);
}
