package com.example.demo.mapper;

import com.example.demo.dto.book.BookResponse;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookMapper {

    public BookResponse toResponse(Book book) {
        BookResponse res = new BookResponse();

        res.setId(book.getId());
        res.setTitle(book.getTitle());

        // Lấy danh sách authorId
        res.setAuthorIds(
                book.getAuthors()
                        .stream()
                        .map(Author::getId)
                        .collect(Collectors.toSet())
        );

        // Lấy publisherId
        res.setPublisherId(book.getPublisher().getId());

        // KHI ĐỂ EAGER Ở BOOK SẼ LẤY ĐC TOÀN BỘ THÔNG TIN NHÀ XUẤT BẢN
        // PublisherResponse pubResp = new PublisherResponse();
        // pubResp.setId(book.getPublisher().getId());
        // pubResp.setName(book.getPublisher().getName());
        // resp.setPublisher(pubResp);

        return res;
    }
}