package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
//@Data
@Getter
@Setter
public class AuthorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private LocalDateTime dateOfBirth;
    private String address;
    private String phoneNumber;

    @OneToOne(mappedBy = "profile")
    private Author author;
}