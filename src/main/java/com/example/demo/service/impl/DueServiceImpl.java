package com.example.demo.service.impl;

import com.example.demo.dto.DueRequestDTO;
import com.example.demo.exception.ApiValidationException;
import com.example.demo.model.Due;
import com.example.demo.model.DueStatus;
import com.example.demo.model.User;
import com.example.demo.repository.DueRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DueService;
import com.example.demo.service.settledues.DueSettleAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class DueServiceImpl implements DueService {

    @Autowired
    DueRepository dueRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DueSettleAlgorithm dueSettleAlgorithm;

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
                .dueStatus(DueStatus.PENDING)
                .build();

        Due due = dueRepository.save(newDue);
        return due;
    }


    /**
     * @param userId
     * @return Returns pending dues for the user
     */
    @Override
    public List<Due> getDues(Long userId) {
        log.info("Getting dues for user: {}", userId);
        User user = userRepository.findById(userId).get();
        if (user == null) {
            throw new ApiValidationException("User not found");
        }
        return user.getDues().stream().filter(due -> due.getDueStatus() != DueStatus.SETTLED).collect(Collectors.toList());
    }

    @Override
    public Boolean settleDues(Long userId) {
        log.info("Settling dues for user :{}", userId);
        User user = userRepository.findById(userId).get();
        if (user == null) {
            throw new ApiValidationException("User not found");
        }
        dueSettleAlgorithm.settleDues(user);
        return true;
    }

    @Override
    public Boolean updateDueStatus(Long id, DueStatus dueStatus) {
        Due due = dueRepository.findById(id).get();
        if (due == null) {
            throw new ApiValidationException("Due not found");
        }
        due.setDueStatus(dueStatus);
        dueRepository.save(due);
        return true;
    }
}