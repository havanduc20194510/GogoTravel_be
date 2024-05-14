package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.response.PaymentResponse;
import com.haduc.go_travel_system.dto.response.VnPayResponse;
import com.haduc.go_travel_system.dto.response.VnPayResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface PaymentService {
    VnPayResponse getVnPayPayment(String bookingId, Double total, String bankCode, String language, HttpServletRequest request) throws UnsupportedEncodingException;
    VnPayResponseCode paymentCallbackHandler(@RequestParam Map<String, String> queryParams, HttpServletResponse response);
    String getVnPayMessage(String vnp_ResponseCode);
    List<PaymentResponse> getPaymentByUserId(String userId);
    List<PaymentResponse> getPaymentByEmail(String email);
    List<PaymentResponse> getPaymentByPhone(String phone);
}
