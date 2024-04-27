package com.haduc.go_travel_system.mapper;

import com.haduc.go_travel_system.dto.request.CreatePlaceRequest;
import com.haduc.go_travel_system.dto.response.PlaceResponse;
import com.haduc.go_travel_system.entity.Place;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlaceMapper {
    Place toEntity(PlaceResponse placeResponse);

    PlaceResponse toDto(Place place);

    Place toPlace(CreatePlaceRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Place partialUpdate(PlaceResponse placeResponse, @MappingTarget Place place);
}
