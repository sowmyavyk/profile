package com.profilemanagement.profile.controller;

import com.profilemanagement.profile.entity.User;
import com.profilemanagement.profile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Login Endpoint
    @PostMapping("/login")
    public String login(@RequestParam String rollNumber, @RequestParam String password) {
        User user = userService.login(rollNumber, password);
        if (user != null) {
            return "Login successful! Welcome " + user.getName();
        }
        return "Invalid roll number or password!";
    }

    // Get User Profile Endpoint
    @GetMapping("/profile/{rollNumber}")
    public User getUserProfile(@PathVariable String rollNumber) {
        return userService.getUserByRollNumber(rollNumber);
    }

    // Update User Profile Endpoint
    @PutMapping("/profile/update")
    public String updateUserProfile(@RequestBody User updatedUser) {
        User user = userService.updateUserProfile(updatedUser);
        if (user != null) {
            return "Profile updated successfully!";
        }
        return "User not found!";
    }
}