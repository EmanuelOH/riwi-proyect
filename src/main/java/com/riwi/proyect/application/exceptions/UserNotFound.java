package com.riwi.proyect.application.exceptions;

import com.riwi.proyect.application.dtos.exceptions.ExceptionBasic;
import com.riwi.proyect.application.dtos.exceptions.ExceptionResponse;
import com.riwi.proyect.application.dtos.exceptions.ExceptionsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(code =  HttpStatus.NOT_FOUND)
public class UserNotFound {
    @ExceptionHandler(UsernameNotFoundException.class)
    public ExceptionBasic userNorFpund(UsernameNotFoundException  exception) {
        return ExceptionResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .message(exception.getMessage())
                .build();
    }
}
