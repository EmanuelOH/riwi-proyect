package com.riwi.proyect.controller.implement;

import com.riwi.proyect.application.dtos.requests.ProjectRequestDto;
import com.riwi.proyect.application.dtos.responses.ProjectResponseDto;
import com.riwi.proyect.controller.interfaces.IProjectController;
import com.riwi.proyect.domain.entities.Project;
import com.riwi.proyect.domain.ports.service.interfaces.IProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projects")
@AllArgsConstructor
@Tag(name = "project", description = "Manages project-related requests.")
@CrossOrigin("*")
public class ProjectControllerImpl implements IProjectController {

    @Autowired
    private final IProjectService projectService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(
            summary = "Create a new project.",
            description = "Provides the project data to create a new project."
    )
    @Override
    public ResponseEntity<Project> create(@Validated @RequestBody ProjectRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.create(requestDto));
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a new project.",
            description = "Deletes a project with the specified ID."
    )
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(
            summary = "Get all project",
            description = "Returns a list of all project."
    )
    @Override
    public ResponseEntity<List<ProjectResponseDto>> readAll() {
        return ResponseEntity.ok(projectService.readAll());
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(
            summary = "Get a project by ID",
            description = "Returns a project with the specified ID."
    )
    @Override
    public ResponseEntity<ProjectResponseDto> readById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.readById(id));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{id}")
    @Operation(
            summary = "Update a project",
            description = "Updates an existing project with the specified ID."
    )
    @Override
    public ResponseEntity<Project> update(@Validated @RequestBody ProjectRequestDto requestDto, @PathVariable Long id) {
        return ResponseEntity.ok(projectService.update(requestDto, id));
    }
}
