package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDateTime repaymentDate;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getUser() {
        return user.getUserId();
    }
}
