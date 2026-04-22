package com.springProjects.smartHabitTracker.repository;

import com.springProjects.smartHabitTracker.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    List<Habit> findByUserId(Long userId);

    boolean existsByUserIdAndName(Long userId, String name);
}
