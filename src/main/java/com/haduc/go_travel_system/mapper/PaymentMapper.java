package com.haduc.go_travel_system.mapper;

import com.haduc.go_travel_system.dto.response.PaymentResponse;
import com.haduc.go_travel_system.entity.Payment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
    Payment toEntity(PaymentResponse paymentResponse);
    @Mapping(target = "bookingId", ignore = true)

    PaymentResponse toDto(Payment payment);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Payment partialUpdate(PaymentResponse paymentResponse, @MappingTarget Payment payment);
}
