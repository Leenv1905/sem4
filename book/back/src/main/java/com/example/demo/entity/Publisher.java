package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

// NHÀ XUẤT BẢN
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "books"})
@Entity
//@Data
@Getter
@Setter
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int yearFounded;

    @OneToMany(mappedBy = "publisher")
    @JsonIgnore  // Nếu không, khi trả JSON sẽ bị vòng lặp vô hạn
    private List<Book> books;
}