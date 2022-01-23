package com.example.demo.model;

import com.example.demo.exception.ApiValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private BigDecimal balance;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // Can think of not deleting due even though user is deleted, may be just use flag to setIsDeleted = false for future reference.
    private List<Due> dues = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // Can think of not deleting expense even though user is deleted, may be just use flag to setIsDeleted = false for future reference.
    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(Double amount) {
        BigDecimal expenseAmount = BigDecimal.valueOf(amount);

        if (this.getBalance().compareTo(expenseAmount) < 0) {
            throw new ApiValidationException("Insufficient balance");
        }
        this.setBalance(this.getBalance().subtract(expenseAmount));
    }
}
