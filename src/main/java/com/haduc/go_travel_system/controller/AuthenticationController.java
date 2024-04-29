package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.AuthenticationRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.AuthenticationResponse;
import com.haduc.go_travel_system.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;
    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
       boolean result = authenticationService.authenticate(request);
       return ApiResponse.<AuthenticationResponse>builder()
               .data(AuthenticationResponse.builder().authenticated(result).build())
               .build();
    }
}
