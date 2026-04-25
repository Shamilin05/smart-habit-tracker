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
    public Habit createHabit(@PathVariable Long userId, @RequestBody Habit habit, @RequestHeader("userId") Long uId){
        return habitService.createHabit(userId, habit, uId);
    }

    @GetMapping("/users/{userId}/habits")
    public List<Habit> getHabits(@PathVariable Long userId, @RequestHeader("userId") Long uId){
        return habitService.getHabits(userId, uId);
    }

    @DeleteMapping("/habit/{habitId}")
    public String deleteHabit(@PathVariable Long habitId, @RequestHeader("userId") Long uId){
        habitService.removeHabit(habitId, uId);
        return "Habit deleted successfully";
    }
}
