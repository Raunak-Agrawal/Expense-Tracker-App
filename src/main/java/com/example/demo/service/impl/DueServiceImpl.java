package com.example.demo.service.impl;

import com.example.demo.exception.ApiValidationException;
import com.example.demo.model.Due;
import com.example.demo.repository.DueRepository;
import com.example.demo.service.DueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class DueServiceImpl implements DueService {

    @Autowired
    DueRepository dueRepository;

    @Override
    public Due save(Due due) {
        log.info("Storing due :{}", due);
        Due newDue = due;
        dueRepository.save(newDue);
        return newDue;
    }

    @Override
    public Boolean deleteDue(Long id) {
        log.info("Deleting due: {}", id);
        Due due = dueRepository.findById(id).get();
        if (due == null) {
            throw new ApiValidationException("Due not found");
        }
        dueRepository.delete(due);
        return true;
    }
}