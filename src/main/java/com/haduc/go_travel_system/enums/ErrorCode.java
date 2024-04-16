package com.haduc.go_travel_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001, "User existed"),
    USERNAME_INVALID(1002, "Username must be at least 4 characters"),
    PASSWORD_INVALID(1003, "Password must be at least 6 characters"),
    ;
    private int code;
    private String message;
}
