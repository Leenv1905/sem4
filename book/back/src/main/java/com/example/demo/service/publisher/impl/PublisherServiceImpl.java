package com.example.demo.service.publisher.impl;

import com.example.demo.dto.publisher.PublisherCreateRequest;
import com.example.demo.dto.publisher.PublisherResponse;
import com.example.demo.dto.publisher.PublisherUpdateRequest;
import com.example.demo.entity.Publisher;
import com.example.demo.mapper.PublisherMapper;
import com.example.demo.repository.PublisherRepository;
import com.example.demo.service.publisher.PublisherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Override
    public PublisherResponse create(PublisherCreateRequest req) {

        if (publisherRepository.existsByName(req.getName())) {
            throw new IllegalArgumentException("Publisher name already exists");
        }

        Publisher publisher = new Publisher();
        publisher.setName(req.getName());
        publisher.setYearFounded(req.getYearFounded());

        Publisher saved = publisherRepository.save(publisher);

        return publisherMapper.toResponse(saved);
    }

    @Override
    public PublisherResponse update(Long id, PublisherUpdateRequest req) {

        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found"));

        if (req.getName() != null) {
            publisher.setName(req.getName());
        }

        if (req.getYearFounded() != 0) {
            publisher.setYearFounded(req.getYearFounded());
        }

        Publisher saved = publisherRepository.save(publisher);

        return publisherMapper.toResponse(saved);
    }

    @Override
    public PublisherResponse getById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found"));
        return publisherMapper.toResponse(publisher);
    }

    @Override
    public List<PublisherResponse> getAll() {
        return publisherRepository.findAll()
                .stream()
                .map(publisherMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!publisherRepository.existsById(id)) {
            throw new EntityNotFoundException("Publisher not found");
        }
        publisherRepository.deleteById(id);
    }
}