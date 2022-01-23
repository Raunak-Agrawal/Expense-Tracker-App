package com.example.demo.service.impl;

import com.example.demo.dto.ExpenseRequestDTO;
import com.example.demo.exception.ApiValidationException;
import com.example.demo.model.Expense;
import com.example.demo.model.User;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Expense save(ExpenseRequestDTO expenseRequestDTO) {
        log.info("Storing expense :{} for user :{}", expenseRequestDTO, expenseRequestDTO.getUserId());

        User user = userRepository.findById(expenseRequestDTO.getUserId()).get();
        if (user == null) {
            throw new ApiValidationException("User doesn't exist");
        }

        user.addExpense(expenseRequestDTO.getAmount());

        Expense newExpense = Expense.builder()
                .user(user)
                .expenseTime(LocalDateTime.now())
                .amount(BigDecimal.valueOf(expenseRequestDTO.getAmount()))
                .location(expenseRequestDTO.getLocation())
                .paymentType(expenseRequestDTO.getPaymentType())
                .reason(expenseRequestDTO.getReason())
                .build();

        Expense expense = expenseRepository.save(newExpense);
        return expense;
    }

    @Override
    public Boolean deleteExpense(Long id) {
        log.info("Deleting expense: {}", id);
        Expense expense = expenseRepository.findById(id).get();
        if (expense == null) {
            throw new ApiValidationException("Expense not found");
        }
        expenseRepository.delete(expense); // Need to work on this to restore expense for the user when expense is deleted
        return true;
    }
}