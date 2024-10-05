package com.riwi.proyect.application.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class AuthUserResponseDto extends UserResponseDto {
    private String message;
    private String token;
}
