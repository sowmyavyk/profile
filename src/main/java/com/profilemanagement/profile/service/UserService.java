package com.profilemanagement.profile.service;

import com.profilemanagement.profile.entity.User;
import com.profilemanagement.profile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String rollNumber, String password) {
        // Find user by roll number and plain-text password
        return userRepository.findByRollNumberAndPassword(rollNumber, password);
    }

    public User getUserByRollNumber(String rollNumber) {
        return userRepository.findByRollNumber(rollNumber);
    }

    public User updateUserProfile(User updatedUser) {
        User existingUser = userRepository.findByRollNumber(updatedUser.getRollNumber());
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                existingUser.setPassword(updatedUser.getPassword()); // Save plain-text password
            }
            return userRepository.save(existingUser);
        }
        return null;
    }
}