package com.pogorzhelskyy.rentme.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please fill the URL")
    private String link;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "housing_id")
    private Housing housing;

    public Photo(String link) {
        this.link = link;
    }

    public Photo(String link, Housing housing) {
        this.link = link;
        this.housing = housing;
    }

    public Photo() {
    }
}
