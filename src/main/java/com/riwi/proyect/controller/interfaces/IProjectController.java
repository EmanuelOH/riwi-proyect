package com.riwi.proyect.controller.interfaces;

import com.riwi.proyect.application.dtos.requests.ProjectRequestDto;
import com.riwi.proyect.application.dtos.responses.ProjectResponseDto;
import com.riwi.proyect.controller.crud.*;
import com.riwi.proyect.domain.entities.Project;

public interface IProjectController extends
        CreateController<ProjectRequestDto, Project>,
        ReadAllController<ProjectResponseDto>,
        ReadByIdController<ProjectResponseDto, Long>,
        UpdateController<ProjectRequestDto, Project, Long>,
        DeleteController<Long> {
}
