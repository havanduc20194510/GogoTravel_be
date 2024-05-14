package com.haduc.go_travel_system.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @Size(min = 4, message = "USERNAME_INVALID")
    private String username;
    @Size(min = 6, message = "PASSWORD_INVALID")
    private String password;
    @Email(message = "EMAIL_INVALID")
    private String email;
}
