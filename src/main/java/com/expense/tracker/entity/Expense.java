package com.expense.tracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false, length = 50)
    private String category; // Food, Travel, Bills

    @Column(length = 255)
    private String description;

    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate;

    @Column(name = "payment_mode", length = 20)
    private String paymentMode; // Cash, UPI, Card

    // Ye line batati hai ki har expense kisi 1 user ka hai
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 1. Default Constructor
    public Expense() {
    }

    // 2. All Args Constructor
    public Expense(Long id, Double amount, String category, String description, 
                   LocalDate expenseDate, String paymentMode, User user) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.expenseDate = expenseDate;
        this.paymentMode = paymentMode;
        this.user = user;
    }

    // 3. GETTERS - Alt+Shift+S → Generate Getters and Setters
    public Long getId() { return id; }
    public Double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public LocalDate getExpenseDate() { return expenseDate; }
    public String getPaymentMode() { return paymentMode; }
    public User getUser() { return user; }

    // 4. SETTERS
    public void setId(Long id) { this.id = id; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setCategory(String category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }
    public void setExpenseDate(LocalDate expenseDate) { this.expenseDate = expenseDate; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    public void setUser(User user) { this.user = user; }
}