package com.riwi.proyect.application.dtos.requests;

import com.riwi.proyect.domain.entities.Project;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
public class TaskRequestDto {
    @NotBlank(message = "name required!")
    private String name;

    @NotBlank(message = "description required!")
    private String description;

    @NotBlank(message = "project required!")
    private Project project;
}
