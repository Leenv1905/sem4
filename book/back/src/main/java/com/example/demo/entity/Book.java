package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
//@Data
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // Many-to-Many với Author
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnore
    private Set<Author> authors;

    // Many-to-One với Publisher
    @ManyToOne(fetch = FetchType.LAZY)
    // CHỖ NÀY NÊN DÙNG EAGER ĐỂ LẤY RA TÊN NHÀ XUẤT BẢN KHI LẤY SÁCH
    @JoinColumn(name = "publisher_id", nullable = false)

    @JsonIgnore
    private Publisher publisher;
}