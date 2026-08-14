package com.expense.tracker.repository;

import com.expense.tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // It will come in handy at the time of login.
    User findByEmail(String email);
    
    // To check during registration whether the email exists or not.
    Boolean existsByEmail(String email);
}
