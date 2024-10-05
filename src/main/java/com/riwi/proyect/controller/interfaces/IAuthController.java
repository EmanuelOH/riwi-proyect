package com.riwi.proyect.controller.interfaces;

import com.riwi.proyect.application.dtos.requests.AuthUserRequestDto;
import com.riwi.proyect.application.dtos.responses.AuthUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface IAuthController {
    public ResponseEntity<AuthUserResponseDto> login (AuthUserRequestDto requestDto);
}
