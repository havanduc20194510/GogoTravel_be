package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.response.VNPayResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface PaymentService {
    VNPayResponse getVnPayPayment(String bookingId, Double total, String bankCode, String language, HttpServletRequest request) throws UnsupportedEncodingException;
}
