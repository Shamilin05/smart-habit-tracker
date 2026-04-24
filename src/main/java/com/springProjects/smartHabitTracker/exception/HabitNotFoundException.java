package com.springProjects.smartHabitTracker.exception;

public class HabitNotFoundException extends RuntimeException{

    public HabitNotFoundException(String message){
        super(message);
    }
}
