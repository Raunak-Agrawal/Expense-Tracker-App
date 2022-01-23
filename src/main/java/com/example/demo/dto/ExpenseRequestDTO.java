package com.example.demo.dto;

import com.example.demo.model.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExpenseRequestDTO {
    private Long userId;
    private String reason;
    private String location;
    private LocalDateTime expenseTime;
    private Double amount;
    private PaymentType paymentType;
}
