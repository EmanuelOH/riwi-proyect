package com.riwi.proyect.application.mappers;

import com.riwi.proyect.application.dtos.requests.UserRequestDto;
import com.riwi.proyect.application.dtos.responses.UserResponseDto;
import com.riwi.proyect.domain.entities.Users;

public class UserMapper {

    //
    public Users toEntity(UserRequestDto requestDto){
        return Users.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .build();
    }

    public UserResponseDto toResponse (Users user){
        return UserResponseDto.builder()
                .build();
    }
}
