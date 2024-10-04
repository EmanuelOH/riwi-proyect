package com.riwi.proyect.controller.implement;

import com.riwi.proyect.application.dtos.requests.ProjectRequestDto;
import com.riwi.proyect.application.dtos.responses.ProjectResponseDto;
import com.riwi.proyect.controller.interfaces.IProjectController;
import com.riwi.proyect.domain.entities.Project;
import com.riwi.proyect.domain.ports.service.interfaces.IProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
@AllArgsConstructor
@Tag(name = "project", description = "Manages project-related requests.")
@CrossOrigin("*")
public class ProjectControllerImpl implements IProjectController {

    @Autowired
    private final IProjectService projectService;

    @Override
    public ResponseEntity<Project> create(@Validated @RequestBody ProjectRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.create(requestDto));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProjectResponseDto>> readAll() {
        return ResponseEntity.ok(projectService.readAll());
    }

    @Override
    public ResponseEntity<ProjectResponseDto> readById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.readById(id));
    }

    @Override
    public ResponseEntity<Project> update(@Validated @RequestBody ProjectRequestDto requestDto, @PathVariable Long id) {
        return ResponseEntity.ok(projectService.update(requestDto, id));
    }
}
