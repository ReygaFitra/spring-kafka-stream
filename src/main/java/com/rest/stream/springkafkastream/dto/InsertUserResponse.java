package com.rest.stream.springkafkastream.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InsertUserResponse<T> {
    private Integer statusCode;
    private String status;
    private String message;
    private T data;
}
