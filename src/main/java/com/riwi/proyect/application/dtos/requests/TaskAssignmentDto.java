package com.riwi.proyect.application.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskAssignmentDto {
    private String userEmail;
    private String projectName;
    private String projectDescription;
}
