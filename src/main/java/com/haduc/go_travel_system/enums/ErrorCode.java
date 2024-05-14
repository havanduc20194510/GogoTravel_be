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


    ;
    private int code;
    private String message;
}
