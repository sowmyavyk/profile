package com.profilemanagement.profile.service;

import com.profilemanagement.profile.entity.User;
import com.profilemanagement.profile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ Fix: Now correctly checks rollNumber, password, and group for login
    public User login(String rollNumber, String password, String group) {
        return userRepository.findByRollNumberAndPasswordAndGroup(rollNumber, password, group);
    }

    public User getUserByRollNumberAndGroup(String rollNumber, String group) {
        return userRepository.findByRollNumberAndGroup(rollNumber, group);
    }

    public User updateUserProfile(User updatedUser) {
        User existingUser = userRepository.findByRollNumberAndGroup(updatedUser.getRollNumber(), updatedUser.getGroup());
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