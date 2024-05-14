package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.config.VnPayConfig;
import com.haduc.go_travel_system.dto.response.PaymentResponse;
import com.haduc.go_travel_system.dto.response.VnPayResponse;
import com.haduc.go_travel_system.dto.response.VnPayResponseCode;
import com.haduc.go_travel_system.entity.BookingTour;
import com.haduc.go_travel_system.entity.DepartureTime;
import com.haduc.go_travel_system.entity.Payment;
import com.haduc.go_travel_system.enums.BookingStatus;
import com.haduc.go_travel_system.enums.PaymentStatus;
import com.haduc.go_travel_system.mapper.PaymentMapper;
import com.haduc.go_travel_system.repository.BookingTourRepository;
import com.haduc.go_travel_system.repository.DepartureTimeRepository;
import com.haduc.go_travel_system.repository.PaymentRepository;
import com.haduc.go_travel_system.service.BookingTourService;
import com.haduc.go_travel_system.service.DepartureTimeService;
import com.haduc.go_travel_system.service.PaymentService;
import com.haduc.go_travel_system.util.VnPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final DepartureTimeRepository departureTimeRepository;
    private final BookingTourRepository bookingTourRepository;

    private final PaymentRepository paymentRepository;


    private final PaymentMapper paymentMapper;


    private final DepartureTimeService departureTimeService;

    private final VnPayConfig vnPayConfig;

    @Override
    public VnPayResponse getVnPayPayment(String bookingId, Double total, String bankCode, String language, HttpServletRequest request) {
        bookingTourRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found!"));
        // change total to numeric
        BigDecimal totalForm = BigDecimal.valueOf(total).setScale(0, RoundingMode.HALF_UP);
        BigDecimal amount = totalForm.multiply(BigDecimal.valueOf(100));

        Map<String, String> vnp_Params = vnPayConfig.getVNPayConfig();
        vnp_Params.put("vnp_Amount",amount.toString());
        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }else {
            vnp_Params.put("vnp_BankCode","NCB");
        }
        vnp_Params.put("vnp_OrderInfo", "Thanh toan ma booking" + bookingId);
        if (language != null && !language.isEmpty()) {
            vnp_Params.put("vnp_Locale", language);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }

        vnp_Params.put("vnp_ReturnUrl", vnPayConfig.getVnp_ReturnUrl() + "?bookingId=" + bookingId);
        vnp_Params.put("vnp_IpAddr", VnPayUtil.getIpAddress(request));
        //build query url
        String queryUrl = VnPayUtil.getPaymentURL(vnp_Params, true);
        String hashData = VnPayUtil.getPaymentURL(vnp_Params, false);
        String vnpSecureHash = VnPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
        return VnPayResponse.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl).build();
    }

    @Override
    public VnPayResponseCode paymentCallbackHandler(Map<String, String> queryParams, HttpServletResponse response) {
        String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
        String bookingId = queryParams.get("bookingId");
        String amount = queryParams.get("vnp_Amount");
        String bankCode = queryParams.get("vnp_BankCode");
        String transactionNo = queryParams.get("vnp_TransactionNo");
        String payDate = queryParams.get("vnp_PayDate");
        String orderInfo = queryParams.get("vnp_OrderInfo");
        String message = getVnPayMessage(vnp_ResponseCode);

        if(bookingId!=null && !bookingId.equals("")){
            if(vnp_ResponseCode.equals("00")){
                // update status booking
                BookingTour bookingTour = bookingTourRepository.findById(bookingId).orElseThrow(()->new RuntimeException("Booking not found!!!"));
                bookingTour.setStatus(BookingStatus.CONFIRMED);
                int totalSeats = bookingTour.getNumberOfAdults() + bookingTour.getNumberOfChildren() + bookingTour.getNumberOfBabies();
                // find departure time
                DepartureTime departureTime = departureTimeRepository.findByTourTourIdAndStartDate(bookingTour.getTour().getTourId(), bookingTour.getStartDate());
                // update booked seats
                departureTimeService.updateBookedSeats(departureTime.getId(), departureTime.getBookedSeats() + totalSeats);
                // update available
                departureTimeService.updateAvailable(departureTime.getId());

                bookingTourRepository.save(bookingTour);
                Payment payment = Payment.builder()
                        .booking(bookingTour)
                        .username(bookingTour.getUser().getUsername())
                        .phone(bookingTour.getPhone())
                        .email(bookingTour.getEmail())
                        .tourName(bookingTour.getTour().getName())
                        .paymentMethod("vnpay")
                        .bankCode(bankCode)
                        .amount(amount)
                        .payDate(payDate)
                        .orderInfo(orderInfo)
                        .transactionNo(transactionNo)
                        .paymentStatus(PaymentStatus.SUCCESS)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                paymentRepository.save(payment);
            }
        }else {
            throw new RuntimeException("Booking not found !!!");
        }
        return VnPayResponseCode.builder()
                .code(vnp_ResponseCode)
                .message(message)
                .build();
    }

    @Override
    public String getVnPayMessage(String vnp_ResponseCode) {
        return switch (vnp_ResponseCode) {
            case "00" -> "Giao dịch thành công";
            case "07" -> "Trừ tiền thành công. Giao dịch bị nghi ngờ";
            case "09" ->
                    "Giao dịch không thành công do: Thẻ/Tài khoản của khách hàng chưa đăng ký dịch vụ InternetBanking tại ngân hàng.";
            case "10" ->
                    "Giao dịch không thành công do: Khách hàng xác thực thông tin thẻ/tài khoản không đúng quá 3 lần.";
            case "11" ->
                    "Giao dịch không thành công do: Đã hết hạn chờ thanh toán. Xin quý khách vui lòng thực hiện lại giao dịch.";
            case "12" -> "Giao dịch không thành công do: Thẻ/Tài khoản của khách hàng bị khóa.";
            case "13" ->
                    "Giao dịch không thành công do: Quý khách nhập sai mật khẩu xác thực giao dịch (OTP). Xin quý khách vui lòng thực hiện lại giao dịch.";
            case "24" -> "Giao dịch không thành công do: Khách hàng hủy giao dịch.";
            case "51" ->
                    "Giao dịch không thành công do: Tài khoản của quý khách không đủ số dư để thực hiện giao dịch.";
            case "65" ->
                    "Giao dịch không thành công do: Tài khoản của Quý khách đã vượt quá hạn mức giao dịch trong ngày.";
            case "75" -> "Ngân hàng thanh toán đang bảo trì.";
            case "79" ->
                    "Giao dịch không thành công do: KH nhập sai mật khẩu thanh toán quá số lần quy định. Xin quý khách vui lòng thực hiện lại giao dịch.";
            case "99" -> "Các lỗi khác (lỗi còn lại, không có trong danh sách mã lỗi đã liệt kê).";
            default -> "Mã lỗi không hợp lệ.";
        };
    }

    @Override
    public List<PaymentResponse> getPaymentByUserId(String userId) {
        List<Payment> payments = paymentRepository.findByBookingUserId(userId);
        List<PaymentResponse> paymentResponses = payments.stream().map(paymentMapper::toDto).toList();
        //add bookingId to paymentResponses
        payments.forEach(payment -> paymentResponses.forEach(paymentResponse -> {
            if(payment.getId().equals(paymentResponse.getId())){
                paymentResponse.setBookingId(payment.getBooking().getId());
            }
        }));

        return paymentResponses;
    }

    @Override
    public List<PaymentResponse> getPaymentByEmail(String email) {
        List<Payment> payments = paymentRepository.findByEmail(email);
        List<PaymentResponse> paymentResponses = payments.stream().map(paymentMapper::toDto).toList();
        //add bookingId to paymentResponses
        payments.forEach(payment -> paymentResponses.forEach(paymentResponse -> {
            if(payment.getId().equals(paymentResponse.getId())){
                paymentResponse.setBookingId(payment.getBooking().getId());
            }
        }));
        return paymentResponses;
    }

    @Override
    public List<PaymentResponse> getPaymentByPhone(String phone) {
        List<Payment> payments = paymentRepository.findByPhone(phone);
        List<PaymentResponse> paymentResponses = payments.stream().map(paymentMapper::toDto).toList();
        //add bookingId to paymentResponses
        payments.forEach(payment -> paymentResponses.forEach(paymentResponse -> {
            if(payment.getId().equals(paymentResponse.getId())){
                paymentResponse.setBookingId(payment.getBooking().getId());
            }
        }));
        return paymentResponses;
    }
}
