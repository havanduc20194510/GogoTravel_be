package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.BookingRequest;
import com.haduc.go_travel_system.dto.request.UpdateBookingRequest;
import com.haduc.go_travel_system.dto.response.BookingResponse;
import com.haduc.go_travel_system.entity.BookingTour;
import com.haduc.go_travel_system.entity.DepartureTime;
import com.haduc.go_travel_system.entity.Tour;
import com.haduc.go_travel_system.entity.User;
import com.haduc.go_travel_system.enums.BookingStatus;
import com.haduc.go_travel_system.enums.ErrorCode;
import com.haduc.go_travel_system.exception.AppException;
import com.haduc.go_travel_system.mapper.BookingTourMapper;
import com.haduc.go_travel_system.repository.BookingTourRepository;
import com.haduc.go_travel_system.repository.DepartureTimeRepository;
import com.haduc.go_travel_system.repository.TourRepository;
import com.haduc.go_travel_system.repository.UserRepository;
import com.haduc.go_travel_system.service.BookingTourService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BookingTourServiceImpl implements BookingTourService {
    private final BookingTourRepository bookingTourRepository;
    private final BookingTourMapper bookingTourMapper;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;

    private final EmailSenderService emailSenderService;
    private final DepartureTimeRepository departureTimeRepository;

    @Override
    public BookingResponse createBookingTour(BookingRequest bookingRequest) {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Tour tour = tourRepository.findById(bookingRequest.getTourId())
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_FOUND));
        List<DepartureTime> departureTimes = tour.getDepartureTimes();
        boolean isContain = departureTimes.stream().anyMatch(departureTime -> departureTime.getStartDate().equals(bookingRequest.getStartDate()));
        if (!isContain) {
            throw new AppException(ErrorCode.DEPARTURE_TIME_NOT_FOUND_IN_TOUR);
        }
        int totalPeople = bookingRequest.getNumberOfAdults()+bookingRequest.getNumberOfChildren()+bookingRequest.getNumberOfBabies();
        DepartureTime departureTime = departureTimeRepository.findByTourTourIdAndStartDate(bookingRequest.getTourId(), bookingRequest.getStartDate());
        int availableSeats = (int) (departureTime.getNumberOfSeats() - departureTime.getBookedSeats());
        if(totalPeople > availableSeats){
            throw new AppException(ErrorCode.OVER_NUMBER_OF_PEOPLE);
        }
        if(totalPeople <=0 || bookingRequest.getNumberOfAdults() < 0 || bookingRequest.getNumberOfChildren() < 0 || bookingRequest.getNumberOfBabies() < 0){
            throw new AppException(ErrorCode.INVALID_NUMBER_OF_PEOPLE);
        }

        if(!departureTime.isAvailable()) {
            throw new AppException(ErrorCode.DEPARTURE_TIME_NOT_AVAILABLE);
        }

        BookingTour bookingTour = bookingTourMapper.toBookingTour(bookingRequest);
        bookingTour.setTour(tour);
        bookingTour.setUser(user);
        LocalDateTime bookingDate = LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .withZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"))
                .toLocalDateTime();
        bookingTour.setBookingDate(bookingDate);
        bookingTour.setStatus(BookingStatus.PENDING);
        Double totalPrice = tour.getAdultPrice() * bookingRequest.getNumberOfAdults() + tour.getChildPrice() * bookingRequest.getNumberOfChildren() + tour.getBabyPrice() * bookingRequest.getNumberOfBabies();
        bookingTour.setTotal(totalPrice);
        BookingTour bookingSaved = bookingTourRepository.save(bookingTour);
        emailSenderService.sendSimpleEmail(bookingRequest.getEmail(), "Booking tour success!!!", "Bạn đã dặt tour thành công, mã đặt tour của bạn là: " + bookingSaved.getId() + ".Hãy thanh toán trước 24h trước khi đi tour. Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi." + "Tour có thể bị hủy nếu vượt quá số lượng người hoặc không thanh toán trước 24h trước khi đi tour.");
        return bookingTourMapper.toDto(bookingSaved);
    }


    @Override
    public BookingResponse updateBookingTour(UpdateBookingRequest updateBookingRequest, String id) {
        BookingTour bookingTour = bookingTourRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));
        bookingTour.setEmail(updateBookingRequest.getEmail());
        bookingTour.setPhone(updateBookingRequest.getPhone());
        List<DepartureTime> departureTimes = bookingTour.getTour().getDepartureTimes();
        boolean isContain = departureTimes.stream().anyMatch(departureTime -> departureTime.getStartDate().equals(updateBookingRequest.getStartDate()));
        if (!isContain) {
            throw new AppException(ErrorCode.DEPARTURE_TIME_NOT_FOUND_IN_TOUR);
        }
        int totalPeople = updateBookingRequest.getNumberOfAdults()+updateBookingRequest.getNumberOfChildren()+updateBookingRequest.getNumberOfBabies();
        DepartureTime departureTime = departureTimeRepository.findByTourTourIdAndStartDate(bookingTour.getTour().getTourId(), updateBookingRequest.getStartDate());
        int availableSeats = (int) (departureTime.getNumberOfSeats() - departureTime.getBookedSeats());
        if(totalPeople > availableSeats){
            throw new AppException(ErrorCode.OVER_NUMBER_OF_PEOPLE);
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
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));
        return bookingTourMapper.toDto(bookingTour);
    }

    @Override
    public List<BookingResponse> getBookingTourByUserId(String userId) {
        List<BookingTour> bookingTours = bookingTourRepository.findByUserIdOrderByBookingDate(userId);
        return bookingTours.stream().map(bookingTourMapper::toDto).toList();
    }

    @Override
    public List<BookingResponse> getBookingTourByPhoneOrEmail(String phone, String email) {
        if(phone == null && email == null){
            List<BookingTour> bookingTours = bookingTourRepository.findAll();
            return bookingTours.stream().map(bookingTourMapper::toDto).toList();
        }
        List<BookingTour> bookingTours = bookingTourRepository.findByPhoneOrEmail(phone, email);
        return bookingTours.stream().map(bookingTourMapper::toDto).toList();
    }

    @Override
    public Double[] getMonthlyTotalForYear(int year) {
            List<Object[]> results = bookingTourRepository.findMonthlyTotalByStatusAndYear(BookingStatus.CONFIRMED, year);
            Double[] monthlyTotals = new Double[12];
            Arrays.fill(monthlyTotals, 0.0);  // Initialize all months to 0.0

            for (Object[] result : results) {
                Integer month = (Integer) result[0];
                Double total = (Double) result[1];
                monthlyTotals[month - 1] = total;  // month is 1-based, array is 0-based
            }

            return monthlyTotals;
    }

    @Override
    public Double getTotalBookingTour(String tourId, int numberOfAdults, int numberOfChildren, int numberOfBabies) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_FOUND));
        return tour.getAdultPrice() * numberOfAdults + tour.getChildPrice() * numberOfChildren + tour.getBabyPrice() * numberOfBabies;
    }
}
