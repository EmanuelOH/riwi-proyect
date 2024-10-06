package com.riwi.proyect.application.dtos.requests;

import com.riwi.proyect.application.dtos.responses.TaskResponseDto;
import com.riwi.proyect.domain.entities.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProjectRequestDto {
    @NotBlank(message = "name required!")
    private String name;

    @NotBlank(message = "description required!")
    private String description;

    @NotEmpty(message = "Task is required")
    private List<TaskRequestDto> tasks;
}
