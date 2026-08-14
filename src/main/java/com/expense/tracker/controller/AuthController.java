package com.expense.tracker.controller;

import com.expense.tracker.dto.UserDto;
import com.expense.tracker.entity.Expense;
import com.expense.tracker.entity.User;
import com.expense.tracker.service.ExpenseService;
import com.expense.tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;
    private ExpenseService expenseService;

    public AuthController(UserService userService, ExpenseService expenseService) {
        this.userService = userService;
        this.expenseService = expenseService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result, Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if (existingUser != null) {
            result.rejectValue("email", null, "Email already registered");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "auth/register";
        }
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findUserByEmail(email);
        
        // 1. User ka naam
        model.addAttribute("userName", user.getName());
        
        // 2. User ke saare expenses
        List<Expense> expenses = expenseService.getExpensesByUserId(user.getId());
        model.addAttribute("expenses", expenses);
        
        // 3. Total amount calculate
        double total = expenses.stream().mapToDouble(Expense::getAmount).sum();
        model.addAttribute("totalAmount", total);
        
        return "dashboard";
    }
}