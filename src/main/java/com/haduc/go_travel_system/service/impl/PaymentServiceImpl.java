package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.config.VnPayConfig;
import com.haduc.go_travel_system.dto.response.VNPayResponse;
import com.haduc.go_travel_system.service.PaymentService;
import com.haduc.go_travel_system.util.VnPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final VnPayConfig vnPayConfig;

    @Override
    public VNPayResponse getVnPayPayment(String bookingId, Double total, String bankCode, String language, HttpServletRequest request) {
        // change total to numeric
        BigDecimal totalForm = BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP);
        BigDecimal amount = totalForm.multiply(BigDecimal.valueOf(100)).setScale(0,RoundingMode.HALF_UP);
        Map<String, String> vnp_Params = vnPayConfig.getVNPayConfig();
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }else {
            vnp_Params.put("vnp_BankCode","NCB");
        }

        if (language != null && !language.isEmpty()) {
            vnp_Params.put("vnp_Locale", language);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }

        vnp_Params.put("vnp_ReturnUrl", vnPayConfig.getVnp_ReturnUrl());
        vnp_Params.put("vnp_IpAddr", VnPayUtil.getIpAddress(request));
        //build query url
        String queryUrl = VnPayUtil.getPaymentURL(vnp_Params, true);
        String hashData = VnPayUtil.getPaymentURL(vnp_Params, false);
        String vnpSecureHash = VnPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
        return VNPayResponse.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl).build();
    }
}
