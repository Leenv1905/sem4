package com.example.demo.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class BookCreateRequest {
    @NotBlank
    @Size (max = 255, min = 1)
    private String title;

//    @NotBlank //KHÔNG dùng cho kiểu số hoặc collection
    @NotEmpty
    private Set<Long> authorIds;
    // Truyền AuthorId để liên kết

    //    @NotBlank //KHÔNG dùng cho kiểu số hoặc collection
    @NotNull
    private Long publisherId;
    // Truyền PublisherId để liên kết

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public Set<Long> getAuthorIds() {return authorIds;}
    public void setAuthorIds(Set<Long> authorIds) {this.authorIds = authorIds;}

    public Long getPublisherId() {return publisherId;}
    public void setPublisherId(Long publisherId) {this.publisherId = publisherId;}



}
