package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.VnPayResponse;
import com.haduc.go_travel_system.service.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/vn-pay/submit")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<VnPayResponse> pay(@RequestParam String bookingId, @RequestParam(defaultValue = "NCB") String bankCode, @RequestParam Double total, @RequestParam(defaultValue = "vn") String language, @RequestParam(defaultValue = "http://localhost:3000/vn-pay/payment-check") String returnUrl, @RequestParam(defaultValue = "false") boolean coin, HttpServletRequest request) throws UnsupportedEncodingException {
        return ApiResponse.<VnPayResponse>builder()
                .code(200)
                .message("Submit payment success!!!")
                .data(paymentService.getVnPayPayment(bookingId,total,bankCode,language,returnUrl,coin, request)).build();
    }
    @GetMapping("/vn-pay/check-payment")
    public String payCallbackHandler(@RequestParam Map<String, String> queryParams, HttpServletResponse response) throws IOException {
        return paymentService.paymentCallbackHandler(queryParams,response);
    }

    @GetMapping("/list/{userId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<?> getPaymentByUserId(@PathVariable String userId) {
        return ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(paymentService.getPaymentByUserId(userId)).build();
    }

    @GetMapping("/list/email/{email}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<?> getPaymentByEmail(@PathVariable String email) {
        return ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(paymentService.getPaymentByEmail(email)).build();
    }

    @GetMapping("/list/phone/{phone}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<?> getPaymentByPhone(@PathVariable String phone) {
        return ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(paymentService.getPaymentByPhone(phone)).build();
    }

}
