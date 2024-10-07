package com.riwi.proyect.application.mappers;

import com.riwi.proyect.application.dtos.requests.ProjectRequestDto;
import com.riwi.proyect.application.dtos.responses.ProjectResponseDto;
import com.riwi.proyect.domain.entities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    Project toEntity (ProjectRequestDto requestDto);

    @Mapping(target = "tasks", source = "tasks")
    @Mapping(target = "users", source = "users")
    ProjectResponseDto toResponseDto (Project project);
}
