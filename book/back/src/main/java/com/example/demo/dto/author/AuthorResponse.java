package com.example.demo.dto.author;

import com.example.demo.dto.authorProfile.AuthorProfileResponse;
import com.example.demo.entity.Book;

import java.util.Set;

public class AuthorResponse {
    private Long id;
    private String name;
//    private Set<Long> bookIds;
    private AuthorProfileResponse profile;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

//    public Set<Long> getBookIds() { return bookIds; }
//    public void setBookIds(Set<Long> bookIds) { this.bookIds = bookIds;}
    // CHỉ lấy BookId thôi

    public AuthorProfileResponse getProfile() { return profile; }
    public void setProfile(AuthorProfileResponse profile) { this.profile = profile; }



}
