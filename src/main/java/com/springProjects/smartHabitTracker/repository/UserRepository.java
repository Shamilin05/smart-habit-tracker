package com.springProjects.smartHabitTracker.repository;

import com.springProjects.smartHabitTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
