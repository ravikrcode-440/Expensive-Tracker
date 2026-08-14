package com.expense.tracker.repository;

import com.expense.tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
    // To retrieve the expenses of only the logged-in user.
    List<Expense> findByUserId(Long userId);
    
    // Category wise filter 
    List<Expense> findByUserIdAndCategory(Long userId, String category);
}
