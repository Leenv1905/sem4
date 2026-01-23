package com.example.demo.repository;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository <Author, Long>
{
    boolean existsByName(String name);

    // JOIN FETCH để lấy luôn books
    @Query("SELECT a FROM Author a left JOIN a.books")
    List<Author> findAllWithBooks();

    @Query("SELECT DISTINCT a FROM Author a JOIN FETCH a.books")
    List<Author> findAllWithBooks2();
}
