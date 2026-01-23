package com.example.demo.dto.publisher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class PublisherCreateRequest {

    @NotBlank(message = "Tên nhà xuất bản không được để trống")
    @Size(max = 150, message = "Tên nhà xuất bản tối đa 150 ký tự")
    private String name;

    @NotNull(message = "Ngày thành lập không được null")
    private int yearFounded;
    // Ở đây không cần có Book vì khi tạo nhà xuất bản thì chưa có sách nào cả

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getYearFounded() {return yearFounded;}
    public void setYearFounded(int yearFounded) {this.yearFounded = yearFounded;}


}
