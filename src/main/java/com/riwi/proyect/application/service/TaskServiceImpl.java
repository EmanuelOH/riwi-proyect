package com.riwi.proyect.application.service;

import com.riwi.proyect.application.dtos.requests.TaskRequestDto;
import com.riwi.proyect.application.dtos.responses.TaskResponseDto;
import com.riwi.proyect.application.mappers.TaskMapper;
import com.riwi.proyect.domain.entities.Tasks;
import com.riwi.proyect.domain.ports.service.interfaces.ITaskService;
import com.riwi.proyect.infrastructure.persistence.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Tasks create(TaskRequestDto taskRequestDto) {
        return this.taskRepository.save(TaskMapper.INSTANCE.toEntity(taskRequestDto));
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
