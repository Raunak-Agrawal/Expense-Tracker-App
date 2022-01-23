package com.example.demo.service.impl;

import com.example.demo.dto.DueRequestDTO;
import com.example.demo.exception.ApiValidationException;
import com.example.demo.model.Due;
import com.example.demo.model.User;
import com.example.demo.repository.DueRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Service
@Transactional
public class DueServiceImpl implements DueService {

    @Autowired
    DueRepository dueRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Due save(DueRequestDTO dueRequestDTO) {
        log.info("Storing due :{} for user :{}", dueRequestDTO, dueRequestDTO.getUserId());

        User user = userRepository.findById(dueRequestDTO.getUserId()).get();

        User owedToUser = userRepository.findById(dueRequestDTO.getOwedTo()).get();

        if (user == null || owedToUser == null) {
            throw new ApiValidationException("User doesn't exist");
        }

        Due newDue = Due.builder()
                .user(user)
                .owedTo(dueRequestDTO.getOwedTo())
                .repaymentDate(dueRequestDTO.getRepaymentDate())
                .amount(BigDecimal.valueOf(dueRequestDTO.getAmount()))
                .build();

        Due due = dueRepository.save(newDue);
        return due;
    }
}