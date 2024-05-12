package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.VNPayResponse;
import com.haduc.go_travel_system.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/vn-pay")
    public ApiResponse<VNPayResponse> pay(HttpServletRequest request) throws UnsupportedEncodingException {
        return ApiResponse.<VNPayResponse>builder()
                .code(200)
                .message("Success")
                .data(paymentService.createVnPayPayment(request)).build();
    }
    @GetMapping("/vn-pay-callback")
    public ApiResponse<VNPayResponse> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            return ApiResponse.<VNPayResponse>builder()
                    .code(200)
                    .message("Success")
                    .data(new VNPayResponse("00","Success","success"))
                    .build();
        } else {
            return ApiResponse.<VNPayResponse>builder()
                    .code(200)
                    .message("Failed")
                    .data(null)
                    .build();
        }
    }
}
