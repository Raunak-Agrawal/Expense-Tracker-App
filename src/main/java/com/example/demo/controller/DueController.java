package com.example.demo.controller;

import com.example.demo.dto.DueRequestDTO;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Due;
import com.example.demo.service.DueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("dues")
public class DueController {

    @Autowired
    private DueService dueService;

    @PostMapping("")
    public ResponseDto<Due> createDue(@Valid @RequestBody DueRequestDTO dueRequestDTO) {
        Due due = dueService.save(dueRequestDTO);
        return ResponseDto.success("Due recorded success", due);
    }

    @GetMapping("")
    public ResponseDto<List<Due>> getDuesForUser(@RequestParam Long userId) {
        return ResponseDto.success("Successfully fetched dues", dueService.getDues(userId));
    }

    @PutMapping("/settle")
    public ResponseDto settleAllDuesForUser(@RequestParam Long userId) {
        return ResponseDto.success("Successfully settled all dues", dueService.settleDues(userId));
    }
}
