package com.riwi.proyect.domain.ports.service.interfaces;

import com.riwi.proyect.application.dtos.requests.UserRequestDto;
import com.riwi.proyect.application.dtos.responses.UserResponseDto;
import com.riwi.proyect.domain.entities.Users;
import com.riwi.proyect.domain.ports.service.crud.*;

public interface IUserService extends
        UpdateService<UserRequestDto, Users, Long>,
        ReadAllService<UserResponseDto>,
        ReadByIdService<UserResponseDto, Long>,
        DeleteService<Long> {

}
