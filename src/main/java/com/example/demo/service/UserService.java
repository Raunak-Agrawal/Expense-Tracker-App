package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    User save(String username);

    User findUserByUsername(String username);

    User findUserById(Long userId);

    Boolean deleteUser(Long id);

}
