package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.AuthenticationRequest;
import com.haduc.go_travel_system.dto.request.IntrospectRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.AuthenticationResponse;
import com.haduc.go_travel_system.dto.response.IntrospectResponse;
import com.haduc.go_travel_system.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
       var result = authenticationService.authenticate(request);
       return ApiResponse.<AuthenticationResponse>builder()
               .data(result)
               .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .data(result)
                .build();
    }
}
