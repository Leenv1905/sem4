package com.example.demo.dto.publisher;

import java.util.List;

public class PublisherResponse {
    private Long id;
    private String name;
    private int yearFounded;
    private List<Long> bookIds;
    // chỉ trả về id danh sách các cuốn sách để gọn nhẹ

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getYearFounded() {return yearFounded;}
    public void setYearFounded(int yearFounded) {this.yearFounded = yearFounded;}

    public List<Long> getBookIds() {return bookIds;}
    public void setBookIds(List<Long> bookIds) {this.bookIds = bookIds;}

}
