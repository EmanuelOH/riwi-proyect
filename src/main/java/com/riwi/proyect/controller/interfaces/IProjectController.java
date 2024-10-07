package com.riwi.proyect.controller.interfaces;

import com.riwi.proyect.application.dtos.requests.ProjectRequestDto;
import com.riwi.proyect.application.dtos.responses.ProjectResponseDto;
import com.riwi.proyect.controller.crud.*;
import com.riwi.proyect.domain.entities.Project;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProjectController extends
        CreateController<ProjectRequestDto, Project>,
        ReadAllController<ProjectResponseDto>,
        ReadByIdController<ProjectResponseDto, Long>,
        UpdateController<ProjectRequestDto, Project, Long>,
        DeleteController<Long>,
        AssignUsersController<Project, Long, Long>{

    public ResponseEntity<List<ProjectResponseDto>> getProject();
}
