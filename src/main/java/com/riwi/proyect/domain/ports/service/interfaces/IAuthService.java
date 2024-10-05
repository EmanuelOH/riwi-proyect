package com.riwi.proyect.domain.ports.service.interfaces;

import com.riwi.proyect.application.dtos.requests.AuthUserRequestDto;
import com.riwi.proyect.application.dtos.responses.AuthUserResponseDto;

public interface IAuthService {
    public AuthUserResponseDto login (AuthUserRequestDto requestDto);
}
