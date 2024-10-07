package com.riwi.proyect.application.dtos.responses;

import com.riwi.proyect.domain.entities.Tasks;
import com.riwi.proyect.domain.entities.Users;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
public class ProjectResponseDto {
    private Long id;

    private String name;

    private String description;

    private Set<UserResponseDto> users;

    private List<TaskResponseDto> tasks;

    private String createdAt;
    
    private String updateAt;
}
