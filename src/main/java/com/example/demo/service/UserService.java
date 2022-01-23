package com.example.demo.service;

import com.example.demo.model.Due;
import com.example.demo.model.Expense;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    User save(String username);

    User findUserByUsername(String username);

    User findUserById(Long userId);

    Boolean deleteUser(Long id);
}
