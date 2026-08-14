package com.expense.tracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserDto {
    
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    
    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;
    
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    // Default Constructor
    public UserDto() {
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}