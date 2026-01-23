package com.example.demo.dto.book;

import java.util.Set;

public class BookUpdateRequest {
    private String title;          // có thể null nếu không muốn đổi
    private Set<Long> authorIds;   // cập nhật danh sách tác giả
    private Long publisherId;      // cập nhật publisher

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public Set<Long> getAuthorIds() {return authorIds;}
    public void setAuthorIds(Set<Long> authorIds) {this.authorIds = authorIds;}

    public Long getPublisherId() {return publisherId;}
    public void setPublisherId(Long publisherId) {this.publisherId = publisherId;}
}
