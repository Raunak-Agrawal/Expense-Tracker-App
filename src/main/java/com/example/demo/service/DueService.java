package com.example.demo.service;

import com.example.demo.dto.DueRequestDTO;
import com.example.demo.model.Due;
import com.example.demo.model.DueStatus;

import java.util.List;

public interface DueService {
    Due save(DueRequestDTO due);
    List<Due> getDues(Long userId);
    Boolean settleDues(Long userId);

    Boolean updateDueStatus(Long id, DueStatus dueStatus);
}
