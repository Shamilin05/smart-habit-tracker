package com.springProjects.smartHabitTracker.service;

import com.springProjects.smartHabitTracker.entity.Habit;
import com.springProjects.smartHabitTracker.entity.HabitCompletion;
import com.springProjects.smartHabitTracker.exception.HabitAlreadyCompletedException;
import com.springProjects.smartHabitTracker.exception.HabitNotFoundException;
import com.springProjects.smartHabitTracker.exception.InvalidAccessException;
import com.springProjects.smartHabitTracker.repository.HabitCompletionRepository;
import com.springProjects.smartHabitTracker.repository.HabitRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitCompletionService {

    private final HabitCompletionRepository habitCompletionRepository;
    private final HabitRepository habitRepository;
    private final HabitService habitService;

    public HabitCompletionService (HabitCompletionRepository habitCompletionRepository, HabitRepository habitRepository, HabitService habitService){
        this.habitCompletionRepository = habitCompletionRepository;
        this.habitRepository = habitRepository;
        this.habitService = habitService;
    }

    public void markTaskAsComplete(Long habitId, Long userId){
        Habit target = habitService.getAuthorizedHabit(habitId, userId);
        LocalDate today = LocalDate.now();
        if (habitCompletionRepository.existsByHabitAndDate(target, today)){
            throw new HabitAlreadyCompletedException("This task has already been marked completed for today!");
        }
        HabitCompletion habitCompletion = new HabitCompletion();
        habitCompletion.setDate(today);
        habitCompletion.setHabit(target);
        try {
            habitCompletionRepository.save(habitCompletion);
        } catch (DataIntegrityViolationException ex) {
            throw new HabitAlreadyCompletedException("This task has already been marked completed for today!");
        }
    }

    public List<HabitCompletion> getTaskCompletionHistory(Long habitId, Long userId){
        Habit target = habitService.getAuthorizedHabit(habitId, userId);
        return habitCompletionRepository.findByHabitId(habitId);
    }

    public int calculateStreak(Long habitId, Long userId){
        Habit target = habitService.getAuthorizedHabit(habitId, userId);
        int streak = 0;
        List<HabitCompletion> completedDates = habitCompletionRepository.findByHabitIdOrderByDateDesc(habitId);
        LocalDate current = LocalDate.now();
        for(int i=0; i< completedDates.size(); i++){
            if(completedDates.get(i).getDate().equals(current)){
                streak++;
                current = current.minusDays(1);
            }
            else{
                break;
            }
        }
        return streak;
    }
}
