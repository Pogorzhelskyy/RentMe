package com.pogorzhelskyy.rentme.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "housing_id")
    private Housing housing;
    @NotBlank(message = "Checkin date is required")
 //   @FutureOrPresent(message = "Checkin date should be today or a future date")
    private LocalDate checkin;
  @NotBlank(message = "Checkout date is required")
//    @Future(message = "Checkin date should be a future date")
    private LocalDate checkout;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User consumer;

    public Booking() {
    }

    public Booking(Housing housing, LocalDate checkin, LocalDate checkout, User consumer) {
        this.housing = housing;
        this.checkin = checkin;
        this.checkout = checkout;
        this.consumer = consumer;
    }

    @Override
    public String toString() {
        return "Checkin - " + checkin +
                ", Checkout - " + checkout;
    }
}
