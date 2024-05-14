package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.VnPayResponse;
import com.haduc.go_travel_system.dto.response.VnPayResponseCode;
import com.haduc.go_travel_system.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/vn-pay")
    public ApiResponse<VnPayResponse> pay(@RequestParam String bookingId, @RequestParam(defaultValue = "NCB") String bankCode, @RequestParam Double total, @RequestParam(defaultValue = "vn") String language, HttpServletRequest request) throws UnsupportedEncodingException {
        return ApiResponse.<VnPayResponse>builder()
                .code(200)
                .message("Success")
                .data(paymentService.getVnPayPayment(bookingId,total,bankCode,language,request)).build();
    }
    @GetMapping("/vn-pay-callback")
    public ApiResponse<VnPayResponseCode> payCallbackHandler(@RequestParam Map<String, String> queryParams, HttpServletResponse response) {
        VnPayResponseCode vnPayResponseCode = paymentService.paymentCallbackHandler(queryParams,response);
        return ApiResponse.<VnPayResponseCode>builder()
                .code(200)
                .message("Payment successful!")
                .data(vnPayResponseCode)
                .build();
    }

    @GetMapping("/list/{userId}")
    public ApiResponse<?> getPaymentByUserId(@PathVariable String userId) {
        return ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(paymentService.getPaymentByUserId(userId)).build();
    }

    @GetMapping("/list/email/{email}")
    public ApiResponse<?> getPaymentByEmail(@PathVariable String email) {
        return ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(paymentService.getPaymentByEmail(email)).build();
    }

    @GetMapping("/list/phone/{phone}")
    public ApiResponse<?> getPaymentByPhone(@PathVariable String phone) {
        return ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(paymentService.getPaymentByPhone(phone)).build();
    }

}
