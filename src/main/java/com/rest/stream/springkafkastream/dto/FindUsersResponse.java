package com.rest.stream.springkafkastream.dto;

import com.rest.stream.springkafkastream.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FindUsersResponse {
    private Integer statusCode;
    private String status;
    private String message;
    private List<User> data;
}
