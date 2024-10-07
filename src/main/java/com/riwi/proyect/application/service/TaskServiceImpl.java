package com.riwi.proyect.application.service;

import com.riwi.proyect.application.dtos.requests.TaskRequestDto;
import com.riwi.proyect.application.dtos.responses.TaskResponseDto;
import com.riwi.proyect.application.mappers.TaskMapper;
import com.riwi.proyect.domain.entities.Project;
import com.riwi.proyect.domain.entities.Tasks;
import com.riwi.proyect.domain.entities.Users;
import com.riwi.proyect.domain.ports.service.interfaces.ITaskService;
import com.riwi.proyect.infrastructure.persistence.ProjectRepository;
import com.riwi.proyect.infrastructure.persistence.TaskRepository;
import com.riwi.proyect.infrastructure.persistence.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Tasks create(TaskRequestDto taskRequestDto) {
        Project project = projectRepository.findById(taskRequestDto.getProject_id())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Tasks task = TaskMapper.INSTANCE.toEntity(taskRequestDto);

        task.setProject(project);

        return this.taskRepository.save(task);
    }

    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found with ID: " + id);
        }

        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskResponseDto> readAll() {
        List<Tasks> tasksList = taskRepository.findAll();

        return tasksList.stream()
                .map(TaskMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto readById(Long id) {
        Tasks task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));

        return TaskMapper.INSTANCE.toResponseDto(task);
    }

    @Override
    public Tasks update(TaskRequestDto taskRequestDto, Long id) {
        return taskRepository.findById(id)
                .map(tasks -> {
                    Tasks task = TaskMapper.INSTANCE.toEntity(taskRequestDto);
                    task.setId(tasks.getId());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " +id));
    }
}
