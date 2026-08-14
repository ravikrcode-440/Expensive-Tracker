package com.expense.tracker.controller;

import com.expense.tracker.entity.Expense;
import com.expense.tracker.entity.User;
import com.expense.tracker.service.ExpenseService;
import com.expense.tracker.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class ExpenseController {

    private ExpenseService expenseService;
    private UserService userService;

    public ExpenseController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    // 1. Add Expense Form Dikhao
    @GetMapping("/expense/new")
    public String showAddExpenseForm(Model model) {
        Expense expense = new Expense();
        expense.setExpenseDate(LocalDate.now()); // Default aaj ki date
        model.addAttribute("expense", expense);
        return "expense/add-expense"; // templates/expense/add-expense.html
    }

    // 2. Expense Save Karo
    @PostMapping("/expense/save")
    public String saveExpense(@ModelAttribute("expense") Expense expense) {
        // Login user nikalo
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findUserByEmail(email);
        
        // Expense me user set karo
        expense.setUser(user);
        expenseService.saveExpense(expense);
        
        return "redirect:/dashboard?success"; // Save hone ke baad dashboard pe bhejo
    }
}