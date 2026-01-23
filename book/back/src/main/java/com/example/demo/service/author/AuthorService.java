package com.example.demo.service.author;

import com.example.demo.dto.author.AuthorCreateRequest;
import com.example.demo.dto.author.AuthorResponse;
import com.example.demo.dto.author.AuthorUpdateRequest;

import java.util.List;

public interface AuthorService {
    AuthorResponse create(AuthorCreateRequest req);
    AuthorResponse getById(Long id);
    List <AuthorResponse> getAll();
    AuthorResponse update(Long id, AuthorUpdateRequest req);
    void delete(Long id);
}
