package com.pogorzhelskyy.rentme.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "housing_id")
    private Housing housing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User consumer;

    public Wish(Housing housing, User consumer) {
        this.housing = housing;
        this.consumer = consumer;
    }

    public Wish() {
    }

    @Override
    public String toString() {
        return  housing.getAddress() +" - " + housing.getPrice();
    }
}
