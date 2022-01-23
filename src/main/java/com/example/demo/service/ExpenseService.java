package com.example.demo.service;

import com.example.demo.dto.ExpenseRequestDTO;
import com.example.demo.model.Expense;

import java.util.List;

public interface ExpenseService {

    Expense save(ExpenseRequestDTO expense);

    Boolean deleteExpense(Long id);

    List<Expense> getExpenses(Long userId);

}
