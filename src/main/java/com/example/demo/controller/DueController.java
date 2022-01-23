package com.example.demo.controller;

import com.example.demo.dto.DueRequestDTO;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Due;
import com.example.demo.service.DueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("dues")
public class DueController {

    @Autowired
    private DueService dueService;

    @PostMapping("")
    public ResponseDto createDue(@Valid @RequestBody DueRequestDTO dueRequestDTO) {
        Due due = dueService.save(dueRequestDTO);
        return ResponseDto.success("Due recorded success", due);
    }
}
