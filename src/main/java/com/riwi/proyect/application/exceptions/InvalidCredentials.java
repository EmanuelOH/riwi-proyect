package com.riwi.proyect.application.exceptions;

import com.riwi.proyect.application.dtos.exceptions.ExceptionBasic;
import com.riwi.proyect.application.dtos.exceptions.ExceptionResponse;
import com.riwi.proyect.domain.exception.InvalidCredentialException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class InvalidCredentials {
    @ExceptionHandler(InvalidCredentialException.class)
    public ExceptionBasic invalidCredentialException(InvalidCredentialException exception){
        return ExceptionResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(HttpStatus.UNAUTHORIZED.name())
                .message(exception.getMessage())
                .build();
    }
}
