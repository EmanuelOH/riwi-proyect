package com.riwi.proyect.application.mappers;

import com.riwi.proyect.application.dtos.requests.TaskRequestDto;
import com.riwi.proyect.application.dtos.responses.TaskResponseDto;
import com.riwi.proyect.domain.entities.Tasks;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Tasks toEntity (TaskRequestDto requestDto);

    TaskResponseDto toResponseDto (Tasks task);
}
