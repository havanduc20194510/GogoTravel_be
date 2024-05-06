package com.haduc.go_travel_system.entity;

import com.haduc.go_travel_system.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingTour {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String email;
    private String phone;
    private LocalDate startDate;
    private int numberOfAdults;
    private int numberOfChildren;
    private int numberOfBabies;
    private String note;
    private LocalDateTime bookingDate;
    private Double total;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
