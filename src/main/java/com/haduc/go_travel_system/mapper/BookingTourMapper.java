package com.haduc.go_travel_system.mapper;

import com.haduc.go_travel_system.dto.request.BookingRequest;
import com.haduc.go_travel_system.dto.response.BookingResponse;
import com.haduc.go_travel_system.entity.BookingTour;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingTourMapper {
    BookingTour toEntity(BookingResponse bookingResponse);

    BookingTour toBookingTour(BookingRequest bookingRequest);

    BookingResponse toDto(BookingTour bookingTour);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookingTour partialUpdate(BookingResponse bookingResponse, @MappingTarget BookingTour bookingTour);
}
