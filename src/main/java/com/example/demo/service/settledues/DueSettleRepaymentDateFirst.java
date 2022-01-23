package com.example.demo.service.settledues;

import com.example.demo.exception.ApiValidationException;
import com.example.demo.model.Due;
import com.example.demo.model.DueStatus;
import com.example.demo.model.User;
import com.example.demo.repository.DueRepository;
import com.example.demo.service.DueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class DueSettleRepaymentDateFirst implements DueSettleAlgorithm {

    @Autowired
    DueService dueService;

    @Autowired
    DueRepository dueRepository;

    @Override
    public Boolean settleDues(User user) {
        List<Due> dues = dueRepository.findAllByUserAndDueStatusOrderByRepaymentDateAsc(user, DueStatus.PENDING); // get all pending dues ordered by repayment date

        if (dues.size() <= 0) {
            throw new ApiValidationException("No dues to settle");
        }
        BigDecimal userBalance = user.getBalance();
        BigDecimal duesAmountToSettle = dues.stream().map(due -> due.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);

        // Check if user has enough balance to settle all dues.
        if (userBalance.compareTo(duesAmountToSettle) < 0) {
            throw new ApiValidationException("Insufficient balance");
        }

        for (Due due : dues) {
            user.addExpense(due.getAmount());
            dueService.updateDueStatus(due.getId(), DueStatus.SETTLED);
        }

        return true;
    }
}
