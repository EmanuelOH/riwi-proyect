package com.riwi.proyect.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskRequestDto {
    @NotBlank(message = "name required!")
    private String name;

    @NotBlank(message = "description required!")
    private String description;
}
