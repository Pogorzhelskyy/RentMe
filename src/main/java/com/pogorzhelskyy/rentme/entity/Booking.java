package com.pogorzhelskyy.rentme.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    private LocalDate checkin;

    private LocalDate checkout;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User consumer;

    public Booking() {
    }

    public Booking(LocalDate checkin, LocalDate checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
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
