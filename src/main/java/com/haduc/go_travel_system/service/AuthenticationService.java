package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.AuthenticationRequest;
import com.haduc.go_travel_system.dto.request.IntrospectRequest;
import com.haduc.go_travel_system.dto.request.LogoutRequest;
import com.haduc.go_travel_system.dto.request.RefreshRequest;
import com.haduc.go_travel_system.dto.response.AuthenticationResponse;
import com.haduc.go_travel_system.dto.response.IntrospectResponse;
import com.haduc.go_travel_system.entity.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    String generateToken(User user);
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
    String buildScope(User user);
    SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException;
    void logout(LogoutRequest request) throws ParseException, JOSEException;
    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
