package com.riwi.proyect.controller.interfaces;

import com.riwi.proyect.application.dtos.requests.UserRequestDto;
import com.riwi.proyect.application.dtos.responses.AuthUserResponseDto;
import com.riwi.proyect.application.dtos.responses.UserResponseDto;
import com.riwi.proyect.controller.crud.*;
import com.riwi.proyect.domain.entities.Users;
import org.springframework.http.ResponseEntity;

public interface IUserController extends
        UpdateController<UserRequestDto, Users, Long>,
        ReadAllController<UserResponseDto>,
        ReadByIdController<UserResponseDto, Long>,
        DeleteController<Long> {
    public ResponseEntity<AuthUserResponseDto> registerAdmin(UserRequestDto requestDto);

    public ResponseEntity<AuthUserResponseDto> registerRegularUser(UserRequestDto requestDto);
}
