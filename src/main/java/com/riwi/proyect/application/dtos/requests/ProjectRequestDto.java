package com.riwi.proyect.application.dtos.requests;

import com.riwi.proyect.domain.entities.Users;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectRequestDto {
    @NotBlank(message = "name required!")
    private String name;

    @NotBlank(message = "description required!")
    private String description;
}
