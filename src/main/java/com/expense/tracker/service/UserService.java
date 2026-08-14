package com.expense.tracker.service;

import com.expense.tracker.dto.UserDto;
import com.expense.tracker.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
}