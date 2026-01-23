package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
// KHÔNG DÙNG @DATA CHO JPA ENTITY VÌ NÓ TẠO RA toString() equals() hashCode()
// SẼ CO THỂ GÂY RA VÒNG LẶP VÔ HẠN KHI CÓ RELATIONSHIP
@Entity
//@Data
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 30)
    private String phone;

}