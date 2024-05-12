package com.haduc.go_travel_system.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VNPayResponse {
    public String code;
    public String message;
    public String paymentUrl;
}
