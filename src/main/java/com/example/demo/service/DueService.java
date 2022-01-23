package com.example.demo.service;

import com.example.demo.model.Due;

public interface DueService {
    Due save(Due due);

    Boolean deleteDue(Long id);

}
