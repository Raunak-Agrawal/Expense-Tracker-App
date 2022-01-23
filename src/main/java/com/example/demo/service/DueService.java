package com.example.demo.service;

import com.example.demo.dto.DueRequestDTO;
import com.example.demo.model.Due;

public interface DueService {
    Due save(DueRequestDTO due);
}
