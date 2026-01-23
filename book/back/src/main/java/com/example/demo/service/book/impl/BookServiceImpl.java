package com.example.demo.service.book.impl;

import com.example.demo.dto.book.BookCreateRequest;
import com.example.demo.dto.book.BookResponse;
import com.example.demo.dto.book.BookUpdateRequest;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Publisher;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;
import com.example.demo.service.book.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponse create(BookCreateRequest req) {

        if (bookRepository.existsByTitle(req.getTitle())) {
            throw new IllegalArgumentException("Book title already exists");
        }

        // Lấy Publisher
        Publisher publisher = publisherRepository.findById(req.getPublisherId())
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found"));

        // Lấy danh sách Author
        Set<Author> authors = new HashSet<>(
                authorRepository.findAllById(req.getAuthorIds())
        );

        if (authors.isEmpty()) {
            throw new IllegalArgumentException("Authors not found");
        }

        // Map DTO -> Entity
        Book book = new Book();
        book.setTitle(req.getTitle());
        book.setPublisher(publisher);
        book.setAuthors(authors);

        Book saved = bookRepository.save(book);

        return bookMapper.toResponse(saved);
    }

    @Override
    public BookResponse update(Long id, BookUpdateRequest req) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        if (req.getTitle() != null) {
            book.setTitle(req.getTitle());
        }

        if (req.getPublisherId() != null) {
            Publisher publisher = publisherRepository.findById(req.getPublisherId())
                    .orElseThrow(() -> new EntityNotFoundException("Publisher not found"));
            book.setPublisher(publisher);
        }

        if (req.getAuthorIds() != null) {
            Set<Author> authors = new HashSet<>(
                    authorRepository.findAllById(req.getAuthorIds())
            );
            book.setAuthors(authors);
        }

        Book saved = bookRepository.save(book);

        return bookMapper.toResponse(saved);
    }

    @Override
    public BookResponse getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return bookMapper.toResponse(book);
    }

    @Override
    public List<BookResponse> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}