package com.example.demo.exception;

import com.example.demo.dto.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<MessageResponse> handleApiValidationException(ApiValidationException e) {
        e.printStackTrace();
        log.error("Got ApiValidationException with Message: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageResponse.builder()
                .message(e.getMessage())
                .details("")
                .build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<MessageResponse> handleException(Exception e) {
        log.error("[Global] Got Internal Server error exception with Message: {}", e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MessageResponse.builder()
                .message("INTERNAL SERVER ERROR")
                .details(e.getMessage())
                .build());
    }

}
