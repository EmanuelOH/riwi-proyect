package com.riwi.proyect.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "username required!")
    private String username;

    @NotBlank(message = "email required!")
    private String email;

    @NotBlank(message = "password required!")
    private String password;
}
