package com.springProjects.smartHabitTracker.exception;

public class HabitAlreadyCompletedException extends RuntimeException{

    public HabitAlreadyCompletedException(String message){
        super(message);
    }
}
