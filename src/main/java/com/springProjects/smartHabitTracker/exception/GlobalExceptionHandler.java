package com.springProjects.smartHabitTracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFound(UserNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(HabitNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleHabitNotFound(HabitNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(HabitAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleHabitExists(HabitAlreadyExistsException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(HabitAlreadyCompletedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateTaskCompletion(HabitAlreadyCompletedException ex){
        return ex.getMessage();
    }
}
