package com.pogorzhelskyy.rentme.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Housing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Type type;
    @Column(columnDefinition = "TEXT", length = 2000)
    @Length(max = 2000, message = "Description is too long (more than 2000)")
    private String description;
    private int square;
    private int rooms;
    private int maxPersons;
    private String city;
    private String address;
    private int price;

    @OneToMany(mappedBy = "housing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set <Booking> bookings;
    @OneToMany(mappedBy = "housing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set <Review> reviews;
    @OneToMany(mappedBy = "housing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <Photo> photos;
    @OneToMany(mappedBy = "housing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <Wish> wishes;

    public Housing() {
    }
}
