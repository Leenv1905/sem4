package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "books"})
@Entity
//@Data
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnore  // Nếu không, khi trả JSON sẽ bị vòng lặp vô hạn
    private Set<Book> books;

    // Đảm bảo ông cháu Jackson không serialize trường này
    @JsonIgnore
    public Set<Book> getBooks() {
        return books;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    // Nghiên cuu Cascade - lưu tự động 2 bảng, xóa tự động prodile khi xóa author
    // BẮT BUỘC NẾU MUỐN LƯU 2 BẢNG CÙNG LÚC QUA RELATIONSHIP
//    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    // khóa ngoại nằm ở bảng author
    // Vì ta chỉ ghi dữ liệu cho Author trong các nghiệp vụ, lúc đó sẽ thêm profile
    // Đây được gọi là “owning side” (bên sở hữu quan hệ)
    private AuthorProfile profile;

}