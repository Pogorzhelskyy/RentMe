package com.pogorzhelskyy.rentme.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "housing_id")
    private Housing housing;
    private Date from;
    private Date until;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User consumer;

    public Booking() {
    }

    public Booking(Housing housing, Date from, Date until, User consumer) {
        this.housing = housing;
        this.from = from;
        this.until = until;
        this.consumer = consumer;
    }
}
