package com.example.demo.dto.user;

import jakarta.validation.constraints.Size;

public class UserUpdateRequest {

    @Size(min = 1, max = 200)
    private String fullName;

    private String password;

    @Size(max = 30)
    private String phone;

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
