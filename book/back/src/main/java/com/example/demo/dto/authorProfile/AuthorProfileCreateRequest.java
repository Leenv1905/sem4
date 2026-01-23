package com.example.demo.dto.authorProfile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class AuthorProfileCreateRequest {
    @NotBlank
    @Size(max = 255, min = 5)
    @Email // Kiểm tra xem có đúng định dạng email ko
    private String email;
    private LocalDateTime dateOfBirth;
    private String address;

    @Pattern(regexp = "^[0-9]{10,11}$", message = "Số điện thoại phải hợp lệ")
    private String phoneNumber;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDateTime dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }


}
