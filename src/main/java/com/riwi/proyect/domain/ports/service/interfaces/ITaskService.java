package com.riwi.proyect.domain.ports.service.interfaces;

import com.riwi.proyect.application.dtos.requests.TaskRequestDto;
import com.riwi.proyect.application.dtos.responses.TaskResponseDto;
import com.riwi.proyect.domain.entities.Tasks;
import com.riwi.proyect.domain.ports.service.crud.*;
import lombok.Builder;
import org.springframework.scheduling.config.Task;

public interface ITaskService extends
        CreateService<TaskRequestDto, Tasks>,
        UpdateService<TaskRequestDto, Tasks, Long>,
        ReadAllService<TaskResponseDto>,
        ReadByIdService<TaskResponseDto, Long>,
        DeleteService<Long> {

}
