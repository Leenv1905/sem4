package com.example.demo.service.publisher;

import com.example.demo.dto.publisher.PublisherCreateRequest;
import com.example.demo.dto.publisher.PublisherResponse;
import com.example.demo.dto.publisher.PublisherUpdateRequest;

import java.util.List;

public interface PublisherService {

    PublisherResponse create(PublisherCreateRequest req);

    PublisherResponse update(Long id, PublisherUpdateRequest req);

    PublisherResponse getById(Long id);

    List<PublisherResponse> getAll();

    void delete(Long id);
}