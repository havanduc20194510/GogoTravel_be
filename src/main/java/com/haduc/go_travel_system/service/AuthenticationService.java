package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.AuthenticationRequest;

public interface AuthenticationService {
    boolean authenticate(AuthenticationRequest request);
}
