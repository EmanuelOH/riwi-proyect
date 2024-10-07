package com.riwi.proyect.controller.implement;

import com.riwi.proyect.application.dtos.requests.TaskRequestDto;
import com.riwi.proyect.application.dtos.responses.TaskResponseDto;
import com.riwi.proyect.controller.interfaces.ITaskController;
import com.riwi.proyect.domain.entities.Tasks;
import com.riwi.proyect.domain.ports.service.interfaces.ITaskService;
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
@RequestMapping("tasks")
@AllArgsConstructor
@Tag(name = "task", description = "Manages task-related requests.")
@CrossOrigin("*")
public class TaskControllerImpl implements ITaskController {

    @Autowired
    private final ITaskService taskService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(
            summary = "Create a new task.",
            description = "Provides the project data to create a new project."
    )
    @Override
    public ResponseEntity<Tasks> create(@Validated @RequestBody TaskRequestDto taskRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(taskRequestDto));
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete a task.",
            description = "Deletes a project with the specified ID."
    )
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(
            summary = "Get all tasks.",
            description = "Returns a list of all tasks."
    )
    @Override
    public ResponseEntity<List<TaskResponseDto>> readAll() {
        return ResponseEntity.ok(taskService.readAll());
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("{id}")
    @Operation(
            summary = "Get a task by ID.",
            description = "Returns a task with the specified ID."
    )
    @Override
    public ResponseEntity<TaskResponseDto> readById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.readById(id));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("{id}")
    @Operation(
            summary = "Updated a task.",
            description = "Updates am existing task with the specified ID."
    )
    @Override
    public ResponseEntity<Tasks> update(@Validated @RequestBody TaskRequestDto taskRequestDto,
                                        @PathVariable Long id) {
        return ResponseEntity.ok(taskService.update(taskRequestDto, id));
    }
}
