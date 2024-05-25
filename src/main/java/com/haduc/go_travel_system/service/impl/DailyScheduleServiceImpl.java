package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.entity.BookingTour;
import com.haduc.go_travel_system.entity.DepartureTime;
import com.haduc.go_travel_system.entity.UserTask;
import com.haduc.go_travel_system.enums.BookingStatus;
import com.haduc.go_travel_system.enums.TaskStatus;
import com.haduc.go_travel_system.repository.BookingTourRepository;
import com.haduc.go_travel_system.repository.DepartureTimeRepository;
import com.haduc.go_travel_system.repository.UserTaskRepository;
import com.haduc.go_travel_system.service.DailyScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyScheduleServiceImpl implements DailyScheduleService {
    private final BookingTourRepository bookingTourRepository;

    private final UserTaskRepository userTaskRepository;

    private final DepartureTimeRepository departureTimeRepository;
    private final EmailSenderService emailSenderService;

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void updateBookingStatus() {
        List<BookingTour> bookingTours = bookingTourRepository.findByStatus(BookingStatus.PENDING);
        LocalDate currentDate = LocalDate.now();

        bookingTours.forEach(bookingTour -> {
            if (currentDate.isAfter(bookingTour.getStartDate().minusDays(1))) {
                bookingTour.setStatus(BookingStatus.CANCELLED);
                bookingTourRepository.save(bookingTour);
                emailSenderService.sendSimpleEmail(bookingTour.getUser().getEmail(), "GogoTravel: Đơn đặt bị hủy!", "Đơn đặt của bạn đã bị hủy do quá thời gian thanh toán!");
            }
        });
    }


    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void updateUserTask() {
        List<UserTask> userTasks = userTaskRepository.findByTaskStatus(TaskStatus.IN_PROGRESS.name());
        LocalDate currentDate = LocalDate.now();
        userTasks.forEach(userTask -> {
            if (currentDate.isAfter(userTask.getTaskDeadline())) {
                userTask.setTaskStatus(TaskStatus.EXPIRED);
                userTaskRepository.save(userTask);
            }
        });
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void updateDepartureTimeAvailable() {
        List<DepartureTime> departureTimes = departureTimeRepository.findAll();
        LocalDate currentDate = LocalDate.now();
        departureTimes.forEach(departureTime -> {
            if (currentDate.isAfter(departureTime.getStartDate().minusDays(1))) {
                departureTime.setAvailable(false);
                departureTimeRepository.save(departureTime);
            }
        });
    }
}
