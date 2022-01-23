package com.example.demo.controller;

import com.example.demo.dto.ExpenseRequestDTO;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Expense;
import com.example.demo.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("")
    public ResponseDto createExpense(@Valid @RequestBody ExpenseRequestDTO expenseRequestDTO) {
        Expense expense = expenseService.save(expenseRequestDTO);
        return ResponseDto.success("Expense recorded success", expense);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<MessageResponse> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseDto.success("Successfully deleted user");
    }
}
