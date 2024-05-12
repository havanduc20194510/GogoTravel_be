package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.response.VNPayResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface PaymentService {
    VNPayResponse createVnPayPayment(HttpServletRequest request) throws UnsupportedEncodingException;
}
