package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.AuthenticationRequest;
import com.haduc.go_travel_system.dto.request.IntrospectRequest;
import com.haduc.go_travel_system.dto.response.AuthenticationResponse;
import com.haduc.go_travel_system.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    String generateToken(String username);
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
