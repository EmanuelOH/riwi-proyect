package com.riwi.proyect.application.dtos.responses;

import com.riwi.proyect.domain.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class TaskResponseDto {
    private Integer id;

    private String name;

    private String description;
}
