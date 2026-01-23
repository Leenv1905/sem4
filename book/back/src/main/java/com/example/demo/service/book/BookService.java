package com.example.demo.service.book;

import com.example.demo.dto.book.BookCreateRequest;
import com.example.demo.dto.book.BookResponse;
import com.example.demo.dto.book.BookUpdateRequest;

import java.util.List;

public interface BookService {

    BookResponse create(BookCreateRequest req);

    BookResponse update(Long id, BookUpdateRequest req);

    BookResponse getById(Long id);

    List<BookResponse> getAll();

    void delete(Long id);
}