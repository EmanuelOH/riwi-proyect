package com.riwi.proyect.application.dtos.responses;

import com.riwi.proyect.domain.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UserResponseDto {
    private Integer id;

    private String username;

    private String email;

    private RoleEnum role;
}
