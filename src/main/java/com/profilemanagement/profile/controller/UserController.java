package com.profilemanagement.profile.controller;

import com.profilemanagement.profile.entity.User;
import com.profilemanagement.profile.service.UserService;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> requestBody) {
        String rollNumber = requestBody.get("rollNumber");
        String password = requestBody.get("password");
        String group = requestBody.get("group");

        User user = userService.login(rollNumber, password, group);
        Map<String, String> response = new HashMap<>();

        if (user != null) {
            response.put("status", "success");
            response.put("message", "Login successful!");
            response.put("name", user.getName());
            response.put("group", user.getGroup());
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Invalid roll number, password, or group!");
            return ResponseEntity.status(401).body(response);
        }
}

    // Get User Profile Endpoint
    @GetMapping("/profile/{rollNumber}/{group}")
    public User getUserProfile(@PathVariable String rollNumber, @PathVariable String group) {
        return userService.getUserByRollNumberAndGroup(rollNumber, group);
    }

    // Update User Profile Endpoint
    @PutMapping("/profile/update")
    public Map<String, String> updateUserProfile(@RequestBody User updatedUser) {
        Map<String, String> response = new HashMap<>();
        User user = userService.updateUserProfile(updatedUser);
        if (user != null) {
            response.put("status", "success");
            response.put("message", "Profile updated successfully!");
        } else {
            response.put("status", "error");
            response.put("message", "User not found!");
        }
        return response;
    }
}