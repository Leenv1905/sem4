package com.example.demo.controller.publisher;

import com.example.demo.dto.publisher.PublisherCreateRequest;
import com.example.demo.dto.publisher.PublisherResponse;
import com.example.demo.dto.publisher.PublisherUpdateRequest;
import com.example.demo.service.publisher.PublisherService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @PostMapping
    public PublisherResponse create(@Valid @RequestBody PublisherCreateRequest req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public PublisherResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<PublisherResponse> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public PublisherResponse update(@PathVariable Long id,
                                    @Valid @RequestBody PublisherUpdateRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}