package com.example.demo.service.user;


import com.example.demo.dto.user.UserCreateRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.dto.user.UserUpdateRequest;

import java.util.List;

public interface UserService {
    UserResponse create(UserCreateRequest req);
    UserResponse getById(Long id);
    List<UserResponse> getAll();
    UserResponse update(Long id, UserUpdateRequest req);
    void delete(Long id);
}
