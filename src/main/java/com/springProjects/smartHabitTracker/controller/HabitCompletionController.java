package com.springProjects.smartHabitTracker.controller;

import com.springProjects.smartHabitTracker.entity.Habit;
import com.springProjects.smartHabitTracker.entity.HabitCompletion;
import com.springProjects.smartHabitTracker.service.HabitCompletionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HabitCompletionController {

    private final HabitCompletionService habitCompletionService;

    public HabitCompletionController(HabitCompletionService habitCompletionService){
        this.habitCompletionService = habitCompletionService;
    }

    @PostMapping("/habits/{habitId}/complete")
    public String markAsComplete(@PathVariable Long habitId){
        habitCompletionService.markTaskAsComplete(habitId);
        return "Well done on completing the task!";
    }

    @GetMapping("habits/{habitId}/completions")
    public List<HabitCompletion> getCompletionHistory(@PathVariable Long habitId){
        return habitCompletionService.getTaskCompletionHistory(habitId);
    }
}
