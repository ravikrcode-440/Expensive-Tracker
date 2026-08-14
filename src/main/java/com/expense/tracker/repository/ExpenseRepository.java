package com.expense.tracker.repository;

import com.expense.tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
    // Sirf login user ke expenses nikalne ke liye
    List<Expense> findByUserId(Long userId);
    
    // Category wise filter ke liye
    List<Expense> findByUserIdAndCategory(Long userId, String category);
}