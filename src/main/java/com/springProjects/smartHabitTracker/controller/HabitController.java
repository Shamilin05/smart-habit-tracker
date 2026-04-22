package com.springProjects.smartHabitTracker.controller;

import com.springProjects.smartHabitTracker.entity.Habit;
import com.springProjects.smartHabitTracker.service.HabitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService){
        this.habitService = habitService;
    }

    @PostMapping("/users/{userId}/habits")
    public Habit createHabit(@PathVariable Long userId, @RequestBody Habit habit){
        return habitService.createHabit(userId, habit);
    }

    @GetMapping("/users/{userId}/habits")
    public List<Habit> getHabits(@PathVariable Long userId){
        return habitService.getHabits(userId);
    }

    @DeleteMapping("/habit/{habitId}")
    public String deleteHabit(@PathVariable Long habitId){
        habitService.removeHabit(habitId);
        return "Habit deleted successfully";
    }
}
