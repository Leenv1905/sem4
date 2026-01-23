package com.example.demo.dto.book;

import com.example.demo.dto.author.AuthorResponse;

import java.util.List;
import java.util.Set;

public class BookResponse {

    private Long id;
    private String title;
    // private List<AuthorResponse> author;
    // Trả về chi tiết tác giả, dễ bị vòng lặp
    private Set<Long> authorIds;
    // Chỉ trả về Id của tác giả
    private Long publisherId;
    // Chỉ trả về Id của nhà xuất bản
//    private PublisherResponse publisher; // thay vì chỉ publisherId
//    Trả về chi tiết nhà xuất bản - KHI ĐỂ EAGER Ở BOOK
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public Set<Long> getAuthorIds() {return authorIds;}
    public void setAuthorIds(Set<Long> authorIds) {this.authorIds = authorIds;}

    public Long getPublisherId() {return publisherId;}
    public void setPublisherId(Long publisherId) {this.publisherId = publisherId;}


}
