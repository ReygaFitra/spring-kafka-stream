package com.rest.stream.springkafkastream.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class StreamOutputResponse<T> {
    private Integer statusCode;
    private String status;
    private UUID userId;
    private String fullName;
}
