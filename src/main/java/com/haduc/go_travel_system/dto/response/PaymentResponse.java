package com.haduc.go_travel_system.dto.response;

import com.haduc.go_travel_system.entity.BookingTour;
import com.haduc.go_travel_system.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private Long id;

    private String bookingId;

    private String username;

    private String phone;

    private String email;

    private String tourName;

    private String amount;

    private String paymentMethod;

    private String bankCode;

    private String transactionNo;

    private String payDate;

    private String orderInfo;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
