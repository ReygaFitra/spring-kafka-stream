package com.rest.stream.springkafkastream.controller;

import com.rest.stream.springkafkastream.dto.InsertUserRequest;
import com.rest.stream.springkafkastream.dto.InsertUserResponse;
import com.rest.stream.springkafkastream.entity.User;
import com.rest.stream.springkafkastream.kafka.KafkaProducer;
import com.rest.stream.springkafkastream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<InsertUserResponse<User>> insertUser(@RequestBody InsertUserRequest request) {
        User userData = userService.saveUser(request);
        kafkaProducer.sendMessage(userData);

        InsertUserResponse<User>response = new InsertUserResponse<>(
                HttpStatus.CREATED.value(),
                "Success",
                "User created successfully",
                userData
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
