package com.riwi.proyect.application.dtos.responses;

import com.riwi.proyect.domain.entities.Auditable;
import com.riwi.proyect.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserResponseDto {
    private String createdBy;

    private String updatedBy;

    private Long id;

    private String username;

    private String email;

    private String role;
}
