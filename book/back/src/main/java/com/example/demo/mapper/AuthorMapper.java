package com.example.demo.mapper;

import com.example.demo.dto.author.AuthorResponse;
import com.example.demo.dto.authorProfile.AuthorProfileResponse;
import com.example.demo.entity.Author;
import com.example.demo.entity.AuthorProfile;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorResponse toResponse(Author author) {
        AuthorResponse dto = new AuthorResponse();
        dto.setId(author.getId());
        dto.setName(author.getName());

        AuthorProfile profile = author.getProfile();
        if (profile != null) {
            AuthorProfileResponse profileDTO = new AuthorProfileResponse();
            profileDTO.setId(profile.getId());
            profileDTO.setEmail(profile.getEmail());
            profileDTO.setDateOfBirth(
                    profile.getDateOfBirth() != null
                            ? profile.getDateOfBirth().toString()
                            : null
            );
            profileDTO.setAddress(profile.getAddress());
            profileDTO.setPhoneNumber(profile.getPhoneNumber());

            dto.setProfile(profileDTO);
        }

        return dto;
    }
}