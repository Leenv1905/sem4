package com.example.demo.mapper;

import com.example.demo.dto.publisher.PublisherResponse;
import com.example.demo.entity.Publisher;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class PublisherMapper {

    public PublisherResponse toResponse(Publisher publisher) {
        PublisherResponse res = new PublisherResponse();

        res.setId(publisher.getId());
        res.setName(publisher.getName());
        res.setYearFounded(publisher.getYearFounded());

        // An toàn tuyệt đối: KHÔNG truy cập vào quan hệ LAZY nếu chưa load
        if (publisher.getBooks() != null) {
            try {
                res.setBookIds(
                        publisher.getBooks()
                                .stream()
                                .map(book -> book.getId())
                                .collect(Collectors.toList())
                );
            } catch (Exception e) {
                // Nếu LazyInitializationException xảy ra → trả về list rỗng
                res.setBookIds(Collections.emptyList());
            }
        } else {
            res.setBookIds(Collections.emptyList());
        }

        return res;
    }
}