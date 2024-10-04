package com.riwi.proyect.domain.ports.service.interfaces;

import com.riwi.proyect.application.dtos.requests.TaskRequestDto;
import com.riwi.proyect.application.dtos.responses.TaskResponseDto;
import com.riwi.proyect.domain.ports.service.crud.*;
import lombok.Builder;
import org.springframework.scheduling.config.Task;

public interface ITaskService extends
        CreateService<TaskRequestDto, Task>,
        UpdateService<TaskRequestDto, Task, Long>,
        ReadAllService<TaskResponseDto>,
        ReadByIdService<TaskResponseDto, Long>,
        DeleteService<Long> {

}
