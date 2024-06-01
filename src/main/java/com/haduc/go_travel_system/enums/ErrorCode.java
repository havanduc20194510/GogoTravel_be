package com.haduc.go_travel_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    EMAIL_INVALID(1000, "Email must be valid"),
    USER_EXISTED(1001, "User existed"),

    USERNAME_INVALID(1002, "Username must be at least 4 characters"),

    PASSWORD_INVALID(1003, "Password must be at least 6 characters"),
    TOUR_NOT_FOUND(1004, "Tour not found"),
    USER_NOT_EXISTED(1005, "User not existed"),
    UNAUTHENTICATED(1006, "Unauthenticated"),

    INVALID_ROLE(1007, "Invalid role"),

    BOOKING_NOT_FOUND(1008, "Booking not found"),

    TOUR_NOT_AVAILABLE(1009, "Tour not available"),

    DEPARTURE_TIME_NOT_FOUND_IN_TOUR(1010, "Departure time not found in this tour"),

    INVALID_NUMBER_OF_PEOPLE(1011, "Invalid number of people"),

    DEPARTURE_TIME_NOT_AVAILABLE(1012, "Departure time is not available"),

    OVER_NUMBER_OF_PEOPLE(1013, "Tổng số chỗ đặt vượt quá số chỗ trống"),

    DEPARTURE_TIME_EXISTED(1014, "Departure time existed"),

    DEPARTURE_TIME_NOT_FOUND(1015, "Departure time not found"),

    PLACE_NOT_FOUND(1016, "Place not found"),


    TOUR_SCHEDULE_NOT_FOUND(1017, "Tour schedule not found"),

    SCHEDULE_DETAIL_NOT_FOUND(1018, "Schedule detail not found"),

    TASK_NOT_FOUND(1019, "Task not found"),

    TOUR_IN_BOOKING_CANNOT_DELETE(1020, "Tour in booking cannot delete"),


    TOUR_TYPE_NOT_FOUND(1021, "Tour type not found"),

    UPLOAD_FILE_FAILED(1022, "Upload file failed"),

    TASK_NOT_IN_PROGRESS(1023, "Task not in progress"),

    TASK_NOT_EXISTED(1024, "Task not existed"),

    CAN_NOT_CREATE_TOKEN(1025, "Can not create token"),

    PASSWORD_ERROR(1026, "Password error"),


    TOUR_REVIEW_NOT_FOUND(1027, "Tour review not found"),

    USER_NOT_BOOKED_TOUR(1028,"Hãy đánh giá tour sau khi trải nghiệm nhé💖!!!" ),

    TASK_ALREADY_DONE(1029, "Task already done"),

    TASK_EXPIRED(1030,"Task expired" ),
    BOOKING_ALREADY_PAID(1031, "Đơn Booking đã thanh toán!" ),
    BOOKING_CANCELLED(1032,"Đơn Booking đã bị hủy!" ),
    INVALID_AMOUNT(1033, "Invalid number" );

    private int code;
    private String message;
}
