package com.riwi.proyect.application.dtos.responses;

import com.riwi.proyect.domain.entities.Users;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class ProjectResponseDto {
    private String name;

    private String description;

    private Users users;

    private List<TaskResponseDto> tasks;
}
