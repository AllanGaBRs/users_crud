package com.allangabr.crud.controllers.handlers;

import com.allangabr.crud.dto.CustomError;
import com.allangabr.crud.dto.FieldMessage;
import com.allangabr.crud.dto.ValidationError;
import com.allangabr.crud.services.exceptions.DatabaseException;
import com.allangabr.crud.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError (
                Instant.now(),
                httpStatus.value(),
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(customError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(
                Instant.now(),
                status.value(),
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidFound(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError customError = new ValidationError (
                Instant.now(),
                httpStatus.value(),
                "Dados inválidos",
                request.getRequestURI());
        for(FieldError f : e.getBindingResult().getFieldErrors()){
            customError.addErrors(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(httpStatus).body(customError);
    }
}
