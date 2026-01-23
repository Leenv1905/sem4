package com.example.demo.controller.book;

import com.example.demo.dto.book.BookCreateRequest;
import com.example.demo.dto.book.BookResponse;
import com.example.demo.dto.book.BookUpdateRequest;
import com.example.demo.service.book.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public BookResponse create(@Valid @RequestBody BookCreateRequest req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<BookResponse> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id,
                               @Valid @RequestBody BookUpdateRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}