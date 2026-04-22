package com.springProjects.smartHabitTracker.repository;

import com.springProjects.smartHabitTracker.entity.Habit;
import com.springProjects.smartHabitTracker.entity.HabitCompletion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Long> {

    boolean existsByHabitAndDate(Habit habit, LocalDate date);

    List<HabitCompletion> findByHabitId(Long habitId);
}
