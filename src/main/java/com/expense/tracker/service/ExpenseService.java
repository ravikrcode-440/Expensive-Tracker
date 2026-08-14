package com.expense.tracker.service;

import com.expense.tracker.entity.Expense;
import java.util.List;

public interface ExpenseService {
    void saveExpense(Expense expense);
    List<Expense> getExpensesByUserId(Long userId);
}