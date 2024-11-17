package com.tests.maicon.resources.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tests.maicon.Exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request) {

        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
