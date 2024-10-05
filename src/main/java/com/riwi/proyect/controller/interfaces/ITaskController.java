package com.riwi.proyect.controller.interfaces;

import com.riwi.proyect.application.dtos.requests.TaskRequestDto;
import com.riwi.proyect.application.dtos.responses.TaskResponseDto;
import com.riwi.proyect.controller.crud.*;
import com.riwi.proyect.domain.entities.Tasks;

public interface ITaskController extends
        CreateController<TaskRequestDto, Tasks>,
        ReadAllController<TaskResponseDto>,
        ReadByIdController<TaskResponseDto, Long>,
        UpdateController<TaskRequestDto, Tasks, Long>,
        DeleteController<Long> {
}
