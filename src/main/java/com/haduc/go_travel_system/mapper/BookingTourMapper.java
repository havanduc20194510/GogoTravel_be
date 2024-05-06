package com.haduc.go_travel_system.mapper;

import com.haduc.go_travel_system.dto.request.BookingRequest;
import com.haduc.go_travel_system.dto.response.BookingResponse;
import com.haduc.go_travel_system.entity.BookingTour;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingTourMapper {
    BookingTour toEntity(BookingRequest bookingRequest);

    BookingResponse toDto(BookingTour bookingTour);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookingTour partialUpdate(BookingRequest bookingRequest, @MappingTarget BookingTour bookingTour);
}
