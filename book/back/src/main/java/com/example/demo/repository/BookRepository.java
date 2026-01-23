package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);

    // JOIN FETCH để lấy luôn authors
    @Query("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.authors")
    List<Book> findAllWithAuthors();

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.authors")
    List<Book> findAllWithAuthors2();
}

