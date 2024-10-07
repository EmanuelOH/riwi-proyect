package com.riwi.proyect.domain.ports.service.interfaces;

import com.riwi.proyect.application.dtos.requests.ProjectRequestDto;
import com.riwi.proyect.application.dtos.responses.ProjectResponseDto;
import com.riwi.proyect.domain.entities.Project;
import com.riwi.proyect.domain.ports.service.crud.*;

import java.util.List;

public interface IProjectService extends
        CreateService<ProjectRequestDto, Project>,
        UpdateService<ProjectRequestDto, Project, Long>,
        ReadAllService<ProjectResponseDto>,
        ReadByIdService<ProjectResponseDto, Long>,
        DeleteService<Long>,
        AssignUsersService<Project, Long, Long> {

    public List<ProjectResponseDto> getProject();
}
