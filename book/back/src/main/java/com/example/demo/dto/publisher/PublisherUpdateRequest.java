package com.example.demo.dto.publisher;

import java.time.LocalDateTime;

public class PublisherUpdateRequest {
    private String name;          // có thể null nếu không muốn đổi
    private int yearFounded;   // có thể null nếu không muốn đổi

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getYearFounded() {return yearFounded;}
    public void setYearFounded(int yearFounded) {this.yearFounded = yearFounded;}
}
