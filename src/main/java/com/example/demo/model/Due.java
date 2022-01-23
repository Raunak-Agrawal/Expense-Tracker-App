package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dues")
@Builder
public class Due {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long owedTo; // Can re-think about this

    private BigDecimal amount;
    private LocalDateTime repaymentDate;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
