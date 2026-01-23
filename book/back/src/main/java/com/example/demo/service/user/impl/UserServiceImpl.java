package com.example.demo.service.user.impl;

import com.example.demo.dto.user.UserCreateRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.dto.user.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse create(UserCreateRequest req) {
        String email = req.getEmail().trim().toLowerCase();

        if (repo.existsByEmail(email)) {
            throw new RuntimeException("Email already exists: " + email);
        }

        User u = new User();
        u.setFullName(req.getFullName().trim());
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setPhone(req.getPhone());

        return toResponse(repo.save(u));
    }

    @Override
    public UserResponse getById(Long id) {
        return toResponse(
                repo.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found: " + id))
        );
    }

    @Override
    public List<UserResponse> getAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public UserResponse update(Long id, UserUpdateRequest req) {
        User u = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));

        if (req.getFullName() != null) u.setFullName(req.getFullName().trim());
        if (req.getPhone() != null) u.setPhone(req.getPhone());

        return toResponse(repo.save(u));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private UserResponse toResponse(User u) {
        UserResponse res = new UserResponse();
        res.setId(u.getId());
        res.setFullName(u.getFullName());
        res.setEmail(u.getEmail());
        res.setPhone(u.getPhone());
        return res;
    }
}





//package com.example.demo.service.user.impl;
//import com.example.demo.dto.user.UserCreateRequest;
//import com.example.demo.dto.user.UserResponse;
//import com.example.demo.dto.user.UserUpdateRequest;
//import com.example.demo.entity.User;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.service.user.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//@Autowired
//UserRepository repo;
//    //    private final UserRepository repo;//field
////
////    public UserServiceImpl(UserRepository repo) {
////        this.repo = repo;
////    }
//
//    @Override
//    public UserResponse create(UserCreateRequest req) {
//        String email = req.getEmail().trim().toLowerCase();
//
//        if (repo.existsByEmail(email)) {
//            throw new RuntimeException("Email already exists: " + email);
//        }
//
//        User c = new User();
//        c.setFullName(req.getFullName().trim());
//        c.setEmail(email);
//        c.setPassword(req.getPassword());
//        c.setPhone(req.getPhone());
//
//        User saved = repo.save(c);
//        return toResponse(saved);
//    }
//
//    @Override
//    public UserResponse getById(Long id) {
//        User c = repo.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found: " + id));
//        return toResponse(c);
//    }
//
//    @Override
//    public List<UserResponse> getAll() {
//        return repo.findAll().stream().map(this::toResponse).toList();
//    }
//
//    @Override
//    public UserResponse update(Long id, UserUpdateRequest req) {
//        User c = repo.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found: " + id));
//
//        if (req.getFullName() != null) c.setFullName(req.getFullName().trim());
//        if (req.getPhone() != null) c.setPhone(req.getPhone());
//
//        User saved = repo.save(c);
//        return toResponse(saved);
//    }
//
//    @Override
//    public void delete(Long id) {
//        if (!repo.existsById(id)) {
//            throw new RuntimeException("User not found: " + id);
//        }
//        repo.deleteById(id);
//    }
//
//    private UserResponse toResponse(User c) {
//        UserResponse res = new UserResponse();
//        res.setId(c.getId());
//        res.setFullName(c.getFullName());
//        res.setEmail(c.getEmail());
//        res.setPhone(c.getPhone());
//        return res;
//        // Ở đây ko map Password vì lý do bảo mật
//    }
//}
//
