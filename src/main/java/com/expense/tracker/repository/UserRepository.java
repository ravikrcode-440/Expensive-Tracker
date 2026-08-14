package com.expense.tracker.repository;

import com.expense.tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Login ke time kaam aayega
    User findByEmail(String email);
    
    // Register ke time check karne ke liye ki email exist karta hai ya nahi
    Boolean existsByEmail(String email);
}