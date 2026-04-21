package com.springProjects.smartHabitTracker.service;

import com.springProjects.smartHabitTracker.entity.User;
import com.springProjects.smartHabitTracker.exception.UserNotFoundException;
import com.springProjects.smartHabitTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User updateUser(long id, User user){
        User target = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not present"));
        target.setName(user.getName());
        target.setEmail(user.getEmail());
        return userRepository.save(target);
    }

    public void deleteUser(long id){
        User target = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist!"));
        userRepository.delete(target);
    }
}
