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

    // Login Endpoint (Updated to accept JSON body)
    @PostMapping("/login")
<<<<<<< HEAD
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> requestBody) {
        String rollNumber = requestBody.get("rollNumber");
        String password = requestBody.get("password");
        String group = requestBody.get("group");

        User user = userService.login(rollNumber, password, group);
        Map<String, String> response = new HashMap<>();

=======
    public String login(@RequestBody User loginRequest) {
        User user = userService.login(loginRequest.getRollNumber(), loginRequest.getPassword(), loginRequest.getGroup());
>>>>>>> e88e64ccb433389f494baccce3ea2c4b7a8e30df
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
    public String updateUserProfile(@RequestBody User updatedUser) {
        User user = userService.updateUserProfile(updatedUser);
        if (user != null) {
            return "Profile updated successfully!";
        }
        return "User not found!";
    }
}
