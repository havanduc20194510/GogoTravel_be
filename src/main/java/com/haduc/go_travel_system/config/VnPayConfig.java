package com.haduc.go_travel_system.config;


import com.haduc.go_travel_system.util.VnPayTimeHelper;
import com.haduc.go_travel_system.util.VnPayUtil;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
@Configuration
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VnPayConfig {
    @Value("${payment.vnPay.url}")
    private String vnp_PayUrl;
    @Value("${payment.vnPay.returnUrl}")
    private String vnp_ReturnUrl;
    @Value("${payment.vnPay.tmnCode}")
    private String vnp_TmnCode ;

    @Value("${payment.vnPay.secretKey}")
    private String secretKey;
    @Value("${payment.vnPay.version}")
    private String vnp_Version;
    @Value("${payment.vnPay.command}")
    private String vnp_Command;
    @Value("${payment.vnPay.orderType}")
    private String orderType;

    public Map<String, String> getVNPayConfig() {
        Map<String, String> vnpParamsMap = new HashMap<>();
        vnpParamsMap.put("vnp_Version", this.vnp_Version);
        vnpParamsMap.put("vnp_Command", this.vnp_Command);
        vnpParamsMap.put("vnp_TmnCode", this.vnp_TmnCode);
        vnpParamsMap.put("vnp_CurrCode", "VND");
        vnpParamsMap.put("vnp_TxnRef", VnPayUtil.getRandomNumber(8));
        vnpParamsMap.put("vnp_OrderType", this.orderType);
        vnpParamsMap.put("vnp_Locale", "vn");
        vnpParamsMap.put("vnp_ReturnUrl", this.vnp_ReturnUrl);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createdDate = VnPayTimeHelper.getCurrentTimeInVnPayFormat();
        LocalDateTime expireDate = VnPayTimeHelper.getExpireTimeInVnPayFormat(createdDate,15);

        vnpParamsMap.put("vnp_CreateDate", formatter.format(createdDate));
        vnpParamsMap.put("vnp_ExpireDate", formatter.format(expireDate));
        return vnpParamsMap;
    }
}
