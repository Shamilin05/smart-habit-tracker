package com.springProjects.smartHabitTracker.exception;

public class HabitAlreadyExistsException extends RuntimeException{

    public HabitAlreadyExistsException(String message){
        super(message);
    }
}
