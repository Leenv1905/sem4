package com.tasc.tascbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "places")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "price")
    private Integer price;
}