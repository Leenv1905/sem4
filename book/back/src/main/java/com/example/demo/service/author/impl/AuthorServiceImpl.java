package com.example.demo.service.author.impl;

import com.example.demo.dto.author.AuthorCreateRequest;
import com.example.demo.dto.author.AuthorResponse;
import com.example.demo.dto.author.AuthorUpdateRequest;
import com.example.demo.dto.authorProfile.AuthorProfileCreateRequest;
import com.example.demo.dto.authorProfile.AuthorProfileResponse;
import com.example.demo.entity.Author;
import com.example.demo.entity.AuthorProfile;
import com.example.demo.entity.Book;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public AuthorResponse create(AuthorCreateRequest req) {
        Author author = new Author();
        author.setName(req.getName());

        AuthorProfileCreateRequest profileReq = req.getProfile();
        AuthorProfile profile = new AuthorProfile();
        profile.setEmail(profileReq.getEmail());
        profile.setAddress(profileReq.getAddress());
        profile.setPhoneNumber(profileReq.getPhoneNumber());
        profile.setDateOfBirth(profileReq.getDateOfBirth());

        author.setProfile(profile);

        Author saved = authorRepository.save(author);
        return authorMapper.toResponse(saved);
    }

    @Override
    public AuthorResponse getById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return authorMapper.toResponse(author);
    }

    @Override
    public List<AuthorResponse> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponse update(Long id, AuthorUpdateRequest req) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        if (req.getName() != null) {
            author.setName(req.getName());
        }

        if (req.getProfile() != null) {
            AuthorProfile profile = author.getProfile();
            if (profile == null) {
                profile = new AuthorProfile();
                author.setProfile(profile);
            }

            if (req.getProfile().getAddress() != null) {
                profile.setAddress(req.getProfile().getAddress());
            }
            if (req.getProfile().getPhoneNumber() != null) {
                profile.setPhoneNumber(req.getProfile().getPhoneNumber());
            }
            if (req.getProfile().getEmail() != null) {
                profile.setEmail(req.getProfile().getEmail());
            }
            if (req.getProfile().getDateOfBirth() != null) {
                profile.setDateOfBirth(LocalDateTime.parse(req.getProfile().getDateOfBirth()));
            }
        }

        Author updated = authorRepository.save(author);
        return authorMapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}



//@Service
//public class AuthorServiceImpl implements AuthorService {
//
////    private final AuthorRepository authorRepository;
////
////    public AuthorServiceImpl(AuthorRepository authorRepository) {
////        this.authorRepository = authorRepository;
////    }
//@Autowired AuthorRepository authorRepository;
//
//    @Override
//    public AuthorResponse create(AuthorCreateRequest req) {
//        // Map DTO -> Entity
//        Author author = new Author();   // Khởi tạo 1 đối tượng author mới
//        author.setName(req.getName());  // Gán tên từ request vào đối tượng author
//
//        AuthorProfileCreateRequest profileReq = req.getProfile();
//        // Tạo profile
//        // Ở ĐÂY NÊẾU MUỐN DỄ HIỂU CÓ THỂ VIẾT TÁCH AUTHOR VÀ AUTHORPROFILE RIÊNG RA
//        // SAU ĐÓ KHI TẠO AUTHOR THÌ TẠO PROFILE RỒI GÁN VÀO ( LÀ THAM SỐ RIÊNG)
//        AuthorProfile profile = new AuthorProfile(); // Khởi tạo đối tợng cho profile
//        profile.setEmail(profileReq.getEmail());  // Lấy email từ request của AuthorProfileCreateRequest
//        profile.setAddress(profileReq.getAddress());  // Tương tự
//        profile.setPhoneNumber(profileReq.getPhoneNumber());
//        profile.setDateOfBirth(profileReq.getDateOfBirth());
//
//        author.setProfile(profile);  // Gán profile vào author qua Setter mặc định
//
//        // Lưu vào DB
//        Author saved = authorRepository.save(author);
//
//        // Map Entity -> Response
//        return toResponse(saved);
//    }
//
//    @Override
//    public AuthorResponse getById(Long id) {
//        Author author = authorRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Author not found"));
//        return toResponse(author);
//    }
//
//    @Override
//    public List<AuthorResponse> getAll() {
//        return authorRepository.findAll()
//                .stream()
//                .map(this::toResponse)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public AuthorResponse update(Long id, AuthorUpdateRequest req) {
//        Author author = authorRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Author not found"));
//
//        if (req.getName() != null) {
//            author.setName(req.getName());
//        }
//        if (req.getProfile() != null) {
//            AuthorProfile profile = author.getProfile();
//            if (profile == null) {
//                profile = new AuthorProfile();
//                author.setProfile(profile);
//            }
//            if (req.getProfile().getAddress() != null) {
//                profile.setAddress(req.getProfile().getAddress());
//            }
//            if (req.getProfile().getPhoneNumber() != null) {
//                profile.setPhoneNumber(req.getProfile().getPhoneNumber());
//            }
//            if (req.getProfile().getEmail() != null) {
//                profile.setEmail(req.getProfile().getEmail());
//            }
//            if (req.getProfile().getDateOfBirth() != null) {
//                profile.setDateOfBirth(LocalDateTime.parse(String.valueOf(req.getProfile().getDateOfBirth())));
//            }
//        }
//
//        Author updated = authorRepository.save(author);
//        return toResponse(updated);
//    }
//
//    @Override
//    public void delete(Long id) {
//        authorRepository.deleteById(id);
//    }
//
//    // Mapper: Entity -> Response
//    private AuthorResponse toResponse(Author author) {
//        AuthorResponse dto = new AuthorResponse();
//        dto.setId(author.getId());
//        dto.setName(author.getName());
//
//        if (author.getProfile() != null) {
//            AuthorProfileResponse profileDTO = new AuthorProfileResponse();
//            profileDTO.setId(author.getProfile().getId());
//            profileDTO.setEmail(author.getProfile().getEmail());
//            profileDTO.setDateOfBirth(String.valueOf(author.getProfile().getDateOfBirth()));  // Chuyển đổi sang String
//            profileDTO.setAddress(author.getProfile().getAddress());
//            profileDTO.setPhoneNumber(author.getProfile().getPhoneNumber());
////            if (author.getBooks() != null) {
////                dto.setBookIds(
////                        author.getBooks()
////                                .stream()
////                                .map(Book::getId)
////                                .collect(Collectors.toSet())
////                );
////            }
//            dto.setProfile(profileDTO);
//        }
//
//        // Nếu muốn trả bookIds thì map sang Set<Long>
//        // dto.setBookIds(author.getBooks().stream().map(Book::getId).collect(Collectors.toSet()));
//
//        return dto;
//    }
//}

