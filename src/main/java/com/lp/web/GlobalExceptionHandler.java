package com.lp.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    public void handleException(Throwable error) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Error: " + error.getMessage(),
                error
        );
    }
}
