package com.riwi.proyect.application.service;

import com.riwi.proyect.application.dtos.requests.ProjectRequestDto;
import com.riwi.proyect.application.dtos.responses.ProjectResponseDto;
import com.riwi.proyect.application.mappers.ProjectMapper;
import com.riwi.proyect.domain.entities.Project;
import com.riwi.proyect.domain.ports.service.interfaces.IProjectService;
import com.riwi.proyect.infrastructure.persistence.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements IProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project create(ProjectRequestDto requestDto) {
        return this.projectRepository.save(ProjectMapper.INSTANCE.toEntity(requestDto));
    }

    @Override
    public void delete(Long id) {
        if(!projectRepository.existsById(id)){
            throw new EntityNotFoundException("Project not found with id: " + id);
        }

        projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectResponseDto> readAll() {
        List<Project> projectList = projectRepository.findAll();

        return projectList.stream()
                .map(ProjectMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDto readById(Long id) {
        Project project = projectRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));

        return ProjectMapper.INSTANCE.toResponseDto(project);
    }

    @Override
    public Project update(ProjectRequestDto requestDto, Long id) {
        return projectRepository.findById(id)
                .map(existingProject -> {
                    Project project = ProjectMapper.INSTANCE.toEntity(requestDto);
                    project.setId(existingProject.getId());
                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
    }
}
