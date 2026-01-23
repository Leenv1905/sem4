package com.example.demo.dto.author;

import com.example.demo.dto.authorProfile.AuthorProfileCreateRequest;
// Gọi ra chi ti profile Author để nhúng vào request khi tạo Author
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class AuthorCreateRequest {
    @NotBlank
    @Size(max = 200)
    private String name;

//    @NotBlank
//    @Size(max = 255, min = 5)
//    @Email // Kiểm tra xem có đúng định dạng email ko
//    private String email;

    // có thể cho phép truyền danh sách bookIds để liên kết
    // Trong trường hợp sách có trước
    // private Set<Long> bookIds;
    // Nên dùng API riêng vd: assignBooksToAuthor(authorId, bookIds))

    @NotNull(message = "Thông tin profile không được null")
    private AuthorProfileCreateRequest profile;

    // === Phần triển khai Get-Set ====////


// Gọi ra chi tiet profile Author để nhúng vào request khi tạo Author
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

//    public String getEmail() {return email;}
//    public void setEmail(String email) {this.email = email;}

    public AuthorProfileCreateRequest getProfile() {return profile;}
    public void setProfile(AuthorProfileCreateRequest profile) {this.profile = profile;}
}
