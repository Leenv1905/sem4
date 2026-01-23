package com.example.demo.dto.authorProfile;

import com.example.demo.entity.Book;

import java.util.Set;

public class AuthorProfileUpdateRequest {
    private String name;
//    private Set<Book> bookIds;
    private AuthorProfileResponse profile;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

//    public Set<Book> getBookIds() { return bookIds; }
//    public void setBookIds(Set<Book> bookIds) { this.bookIds = bookIds;}
//    // CHỉ lấy BookId thôi

    public AuthorProfileResponse getProfile() { return profile; }
    public void setProfile(AuthorProfileResponse profile) { this.profile = profile; }



}
