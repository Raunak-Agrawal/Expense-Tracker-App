package com.example.demo.service;

import com.example.demo.dto.ExpenseRequestDTO;
import com.example.demo.model.Expense;

public interface ExpenseService {

    Expense save(ExpenseRequestDTO expense);

    Boolean deleteExpense(Long id);

}
