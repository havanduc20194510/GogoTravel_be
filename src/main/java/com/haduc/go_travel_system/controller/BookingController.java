package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.BookingRequest;
import com.haduc.go_travel_system.dto.request.UpdateBookingRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.BookingResponse;
import com.haduc.go_travel_system.dto.response.StatisticResponse;
import com.haduc.go_travel_system.service.BookingTourService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
public class BookingController {
    private final BookingTourService bookingTourService;
    @PostMapping("/create")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<BookingResponse> createBookingTour(@RequestBody @Valid BookingRequest bookingRequest) {
        BookingResponse bookingResponse = bookingTourService.createBookingTour(bookingRequest);
        return ApiResponse.<BookingResponse>builder()
                .message("Create booking tour successfully")
                .data(bookingResponse)
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<BookingResponse> updateBookingTour(@RequestBody @Valid UpdateBookingRequest updateBookingRequest, @PathVariable String id) {
        BookingResponse bookingResponse = bookingTourService.updateBookingTour(updateBookingRequest, id);
        return ApiResponse.<BookingResponse>builder()
                .message("Update booking tour successfully")
                .data(bookingResponse)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteBookingTour(@PathVariable String id) {
        String message = bookingTourService.deleteBookingTour(id);
        return ApiResponse.<String>builder()
                .message(message)
                .data("Delete booking tour successfully")
                .build();
    }

    @GetMapping("/all")
    public ApiResponse<List<BookingResponse>> getAllBookingTours() {
        List<BookingResponse> bookingResponses = bookingTourService.getAllBookingTours();
        if (bookingResponses.isEmpty()) {
            return ApiResponse.<List<BookingResponse>>builder()
                    .message("No booking tours found")
                    .data(bookingResponses)
                    .build();
        }
        return ApiResponse.<List<BookingResponse>>builder()
                .message("Get all booking tours successfully")
                .data(bookingResponses)
                .build();
    }

    @GetMapping("/get/{id}")
    public ApiResponse<BookingResponse> getBookingTourById(@PathVariable String id) {
        BookingResponse bookingResponse = bookingTourService.getBookingTourById(id);
        if(bookingResponse == null) {
            return ApiResponse.<BookingResponse>builder()
                    .message("No booking tour found")
                    .data(null)
                    .build();
        }

        return ApiResponse.<BookingResponse>builder()
                .message("Get booking tour by id successfully")
                .data(bookingResponse)
                .build();
    }

    @GetMapping("/get-by-user/{userId}")
    public ApiResponse<List<BookingResponse>> getBookingTourByUserId(@PathVariable String userId) {
        List<BookingResponse> bookingResponses = bookingTourService.getBookingTourByUserId(userId);
        if(bookingResponses.isEmpty()) {
            return ApiResponse.<List<BookingResponse>>builder()
                    .message("No booking tour found")
                    .data(null)
                    .build();
        }
        return ApiResponse.<List<BookingResponse>>builder()
                .message("Get booking tour by user id successfully")
                .data(bookingResponses)
                .build();
    }

    @GetMapping("/get-by-phone-or-email")
    public ApiResponse<List<BookingResponse>> getBookingTourByPhoneOrEmail(@RequestParam(required = false) String phone, @RequestParam(required = false) String email) {
        List<BookingResponse> bookingResponses = bookingTourService.getBookingTourByPhoneOrEmail(phone, email);
        if(bookingResponses.isEmpty()) {
            return ApiResponse.<List<BookingResponse>>builder()
                    .message("No booking tour found")
                    .data(null)
                    .build();
        }
        return ApiResponse.<List<BookingResponse>>builder()
                .message("Get booking tour by phone or email successfully")
                .data(bookingResponses)
                .build();
    }

    @GetMapping("/get-monthly-total")
    public ApiResponse<Double[]> getMonthlyTotalForYear(@RequestParam int year) {
        Double[] monthlyTotals = bookingTourService.getMonthlyTotalForYear(year);
        return ApiResponse.<Double[]>builder()
                .message("Get monthly total for year successfully")
                .data(monthlyTotals)
                .build();
    }

    @GetMapping("/get-total")
    public ApiResponse<Double> getTotalBookingTour(@RequestParam String tourId, @RequestParam int numberOfAdults, @RequestParam int numberOfChildren, @RequestParam int numberOfBabies) {
        Double total = bookingTourService.getTotalBookingTour(tourId, numberOfAdults, numberOfChildren, numberOfBabies);
        return ApiResponse.<Double>builder()
                .message("Get total booking tour successfully")
                .data(total)
                .build();
    }

    @GetMapping("/get-total-guests")
    public ApiResponse<StatisticResponse> getTotalGuestsByMonth() {
        StatisticResponse totalGuests = bookingTourService.getTotalGuestsByMonth();
        return ApiResponse.<StatisticResponse>builder()
                .message("Get total guests by month successfully")
                .data(totalGuests)
                .build();
    }

}
