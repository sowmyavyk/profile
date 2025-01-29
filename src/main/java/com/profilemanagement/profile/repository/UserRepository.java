package com.profilemanagement.profile.repository;

import com.profilemanagement.profile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByRollNumberAndGroup(String rollNumber, String group);
    
    // ✅ Fix: Add this method to validate login credentials
    User findByRollNumberAndPasswordAndGroup(String rollNumber, String password, String group);
}