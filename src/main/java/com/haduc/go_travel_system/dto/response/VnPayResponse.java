package com.haduc.go_travel_system.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VnPayResponse {
    public String code;
    public String message;
    public String paymentUrl;
}
