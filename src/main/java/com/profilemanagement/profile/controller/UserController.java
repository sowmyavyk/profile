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

    // Login Endpoint (Updated to accept JSON body)
    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        User user = userService.login(loginRequest.getRollNumber(), loginRequest.getPassword(), loginRequest.getGroup());
        if (user != null) {
            return "Login successful! Welcome " + user.getName() + " from " + user.getGroup();
        }
        return "Invalid roll number, password, or group!";
    }

    // Get User Profile Endpoint
    @GetMapping("/profile/{rollNumber}/{group}")
    public User getUserProfile(@PathVariable String rollNumber, @PathVariable String group) {
        return userService.getUserByRollNumberAndGroup(rollNumber, group);
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
