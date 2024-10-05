package com.riwi.proyect.application.mappers;

import com.riwi.proyect.application.dtos.requests.UserRequestDto;
import com.riwi.proyect.application.dtos.responses.UserResponseDto;
import com.riwi.proyect.domain.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users toEntity (UserRequestDto requestDto);
    UserResponseDto toResponseDto (Users user);

}
