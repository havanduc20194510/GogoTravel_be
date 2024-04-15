package com.haduc.go_travel_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private LocalDate bookingDate;
    private int numberOfAdults;
    private int numberOfChildren;
    private String note;
    private Double total;
    private String status;
    private String paymentMethod;
    private String paymentStatus;
}
