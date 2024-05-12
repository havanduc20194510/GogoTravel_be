package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.BookingRequest;
import com.haduc.go_travel_system.dto.request.UpdateBookingRequest;
import com.haduc.go_travel_system.dto.response.BookingResponse;
import com.haduc.go_travel_system.entity.BookingTour;
import com.haduc.go_travel_system.entity.DepartureTime;
import com.haduc.go_travel_system.entity.Tour;
import com.haduc.go_travel_system.entity.User;
import com.haduc.go_travel_system.enums.BookingStatus;
import com.haduc.go_travel_system.mapper.BookingTourMapper;
import com.haduc.go_travel_system.repository.BookingTourRepository;
import com.haduc.go_travel_system.repository.TourRepository;
import com.haduc.go_travel_system.repository.UserRepository;
import com.haduc.go_travel_system.service.BookingTourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BookingTourServiceImpl implements BookingTourService {
    private final BookingTourRepository bookingTourRepository;
    private final BookingTourMapper bookingTourMapper;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;

    private final EmailSenderService emailSenderService;

    @Override
    public BookingResponse createBookingTour(BookingRequest bookingRequest) {
        Tour tour = tourRepository.findById(bookingRequest.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour not found"));
        User user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<DepartureTime> departureTimes = tour.getDepartureTimes();
        boolean isContain = departureTimes.stream().anyMatch(departureTime -> departureTime.getStartDate().equals(bookingRequest.getStartDate()));
        if (!isContain) {
            throw new RuntimeException("Tour not contain this start date");
        }
        BookingTour bookingTour = bookingTourMapper.toBookingTour(bookingRequest);
        bookingTour.setTour(tour);
        bookingTour.setUser(user);
        LocalDateTime bookingDate = LocalDateTime.now();
        bookingTour.setBookingDate(bookingDate);
        bookingTour.setStatus(BookingStatus.PENDING);
        bookingTour.setTotal(tour.getAdultPrice() * bookingRequest.getNumberOfAdults() + tour.getChildPrice() * bookingRequest.getNumberOfChildren() + tour.getBabyPrice() * bookingRequest.getNumberOfBabies());
        BookingTour bookingSaved = bookingTourRepository.save(bookingTour);
        emailSenderService.sendSimpleEmail(bookingRequest.getEmail(), "Booking tour success!!!", "Bạn dã dặt tour thành công, mã đặt tour của bạn là: " + bookingSaved.getId() + ".Hãy thanh toán trước 24h trước khi đi tour. Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.");
        return bookingTourMapper.toDto(bookingSaved);
    }

    @Override
    public BookingResponse updateBookingTour(UpdateBookingRequest updateBookingRequest, String id) {
        BookingTour bookingTour = bookingTourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking tour not found"));
        bookingTour.setEmail(updateBookingRequest.getEmail());
        bookingTour.setPhone(updateBookingRequest.getPhone());
        List<DepartureTime> departureTimes = bookingTour.getTour().getDepartureTimes();
        boolean isContain = departureTimes.stream().anyMatch(departureTime -> departureTime.getStartDate().equals(updateBookingRequest.getStartDate()));
        if (!isContain) {
            throw new RuntimeException("Tour not contain this start date");
        }
        bookingTour.setStartDate(updateBookingRequest.getStartDate());
        bookingTour.setNumberOfAdults(updateBookingRequest.getNumberOfAdults());
        bookingTour.setNumberOfChildren(updateBookingRequest.getNumberOfChildren());
        bookingTour.setNumberOfBabies(updateBookingRequest.getNumberOfBabies());
        bookingTour.setNote(updateBookingRequest.getNote());
        bookingTour.setTotal(bookingTour.getTour().getAdultPrice() * updateBookingRequest.getNumberOfAdults() + bookingTour.getTour().getChildPrice() * updateBookingRequest.getNumberOfChildren() + bookingTour.getTour().getBabyPrice() * updateBookingRequest.getNumberOfBabies());
        return bookingTourMapper.toDto(bookingTourRepository.save(bookingTour));
    }

    @Override
    public String deleteBookingTour(String id) {
        bookingTourRepository.deleteById(id);
        return "Delete booking tour success";
    }

    @Override
    public List<BookingResponse> getAllBookingTours() {
        List<BookingTour> bookingTours = bookingTourRepository.findAll();
        return bookingTours.stream().map(bookingTourMapper::toDto).toList();
    }

    @Override
    public BookingResponse getBookingTourById(String id) {
        BookingTour bookingTour = bookingTourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking tour not found"));
        return bookingTourMapper.toDto(bookingTour);
    }

    @Override
    public List<BookingResponse> getBookingTourByUserId(String userId) {
        List<BookingTour> bookingTours = bookingTourRepository.findByUserId(userId);
        return bookingTours.stream().map(bookingTourMapper::toDto).toList();
    }
}
