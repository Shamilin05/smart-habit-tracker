package com.springProjects.smartHabitTracker.service;

import com.springProjects.smartHabitTracker.entity.Habit;
import com.springProjects.smartHabitTracker.entity.User;
import com.springProjects.smartHabitTracker.exception.HabitAlreadyExistsException;
import com.springProjects.smartHabitTracker.exception.HabitNotFoundException;
import com.springProjects.smartHabitTracker.exception.InvalidAccessException;
import com.springProjects.smartHabitTracker.exception.UserNotFoundException;
import com.springProjects.smartHabitTracker.repository.HabitRepository;
import com.springProjects.smartHabitTracker.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitService(HabitRepository habitRepository, UserRepository userRepository){
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    public Habit getAuthorizedHabit(Long habitId, Long userId){
        Habit target = habitRepository.findById(habitId).orElseThrow(() -> new HabitNotFoundException("Habit not found"));
        if(!target.getUser().getId().equals(userId)){
            throw new InvalidAccessException("You don't have access to this habit!");
        }
        return target;
    }

    public Habit createHabit(Long userId, Habit habit, Long uId){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User is not in db"));
        if(!user.getId().equals(uId)){
            throw new InvalidAccessException("You cannot access this habit!");
        }
        habit.setName(habit.getName().toLowerCase());
        if(habitRepository.existsByUserIdAndName(userId, habit.getName())) {
            throw new HabitAlreadyExistsException("Habit already exists for this user");
        }
        habit.setUser(user);
        try {
            return habitRepository.save(habit);
        } catch (DataIntegrityViolationException ex) {
            throw new HabitAlreadyExistsException("Habit already exists for this user");
        }
    }

    public List<Habit> getHabits(Long userId, Long uId){
        User target = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Invalid user provided"));
        if(!target.getId().equals(uId)){
            throw new InvalidAccessException("You don't have access to this habit");
        }
        return habitRepository.findByUserId(userId);
    }

    public void removeHabit(Long habitId, Long uId){
        Habit target = getAuthorizedHabit(habitId, uId);
        habitRepository.delete(target);
    }
}
