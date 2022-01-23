package com.example.demo.controller;

import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.exception.ApiValidationException;
import com.example.demo.model.Due;
import com.example.demo.model.Expense;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseDto resetPassword(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        User existingUser = userService.findUserByUsername(userRequestDTO.getUsername());
        if (existingUser != null) {
            throw new ApiValidationException("User already exists");
        }
        userService.save(userRequestDTO.getUsername());
        return ResponseDto.success("User registered success");
    }

    @DeleteMapping("/{id}")
    public ResponseDto<MessageResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseDto.success("Successfully deleted user");
    }
}
