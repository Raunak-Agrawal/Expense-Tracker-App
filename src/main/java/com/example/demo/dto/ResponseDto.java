package com.example.demo.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
public class ResponseDto<T> {

    private String message;
    private T data;

    protected ResponseDto(T data) {
        this.data = data;
    }

    protected ResponseDto(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(data);
    }

    public static <T> ResponseDto<T> success(String message, T data) {
        return new ResponseDto<>(message, data);
    }

    public static <T> ResponseDto<T> success(String message) {
        return new ResponseDto<>(message, null);
    }

}
