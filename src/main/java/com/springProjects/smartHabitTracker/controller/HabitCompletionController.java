package com.springProjects.smartHabitTracker.controller;

import com.springProjects.smartHabitTracker.entity.HabitCompletion;
import com.springProjects.smartHabitTracker.service.HabitCompletionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HabitCompletionController {

    private final HabitCompletionService habitCompletionService;

    public HabitCompletionController(HabitCompletionService habitCompletionService){
        this.habitCompletionService = habitCompletionService;
    }

    @PostMapping("/habits/{habitId}/complete")
    public String markAsComplete(@PathVariable Long habitId, @RequestHeader Long userId){
        habitCompletionService.markTaskAsComplete(habitId, userId);
        return "Well done on completing the task!";
    }

    @GetMapping("/habits/{habitId}/completions")
    public List<HabitCompletion> getCompletionHistory(@PathVariable Long habitId, @RequestHeader Long userId){
        return habitCompletionService.getTaskCompletionHistory(habitId, userId);
    }

    @GetMapping("/habits/{habitId}/streak")
    public int getStreak(@PathVariable Long habitId, @RequestHeader Long userId){
        return habitCompletionService.calculateStreak(habitId, userId);
    }
}
