package com.riwi.proyect.application.exceptions;

import com.riwi.proyect.application.dtos.exceptions.ExceptionBasic;
import com.riwi.proyect.application.dtos.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError {
    @ExceptionHandler(Exception.class)
    public ExceptionBasic handleInternalServerError(Exception exception) {
        return ExceptionResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message("An internal server error occurred: " + exception.getMessage())
                .build();
    }
}
