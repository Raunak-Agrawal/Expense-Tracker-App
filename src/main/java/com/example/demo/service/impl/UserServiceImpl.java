package com.example.demo.service.impl;

import com.example.demo.exception.ApiValidationException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(String username) {
        log.info("Storing user with username :{}", username);
        User newUser = User.builder().username(username).balance(BigDecimal.valueOf(1000)).build(); // adding initial balance for user as 1000; We can use Balance class as entity to track this as well if we want and have one-to-one relation.
        User user = userRepository.save(newUser);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        if (user == null) {
            throw new ApiValidationException("User not found");
        }
        return user;
    }

    @Override
    public Boolean deleteUser(Long id) {
        log.info("Deleting user: {}", id);
        User user = findUserById(id);
        userRepository.delete(user);
        return true;
    }
}
