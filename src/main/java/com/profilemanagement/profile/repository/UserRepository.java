package com.profilemanagement.profile.repository;


import com.profilemanagement.profile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByRollNumberAndPasswordAndGroup(String rollNumber, String password, String group);
    User findByRollNumberAndGroup(String rollNumber, String group);
}