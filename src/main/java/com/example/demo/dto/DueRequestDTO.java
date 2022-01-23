package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DueRequestDTO {
    private Long userId; // Added by user
    private Long owedTo; // Can re-think about this
    private Double amount;
    private LocalDateTime repaymentDate;
}
