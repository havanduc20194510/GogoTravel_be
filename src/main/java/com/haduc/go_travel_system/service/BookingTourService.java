package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.BookingRequest;
import com.haduc.go_travel_system.dto.request.UpdateBookingRequest;
import com.haduc.go_travel_system.dto.response.BookingResponse;

import java.util.List;

public interface BookingTourService {
    BookingResponse createBookingTour(BookingRequest bookingRequest);

    BookingResponse updateBookingTour(UpdateBookingRequest updateBookingRequest, String id);

    String deleteBookingTour(String id);

    List<BookingResponse> getAllBookingTours();

    BookingResponse getBookingTourById(String id);

    List<BookingResponse> getBookingTourByUserId(String userId);
}
