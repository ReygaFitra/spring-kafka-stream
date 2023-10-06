package com.rest.stream.springkafkastream.controller;

import com.rest.stream.springkafkastream.dto.FindUserResponse;
import com.rest.stream.springkafkastream.dto.FindUsersResponse;
import com.rest.stream.springkafkastream.dto.InsertUserRequest;
import com.rest.stream.springkafkastream.dto.InsertUserResponse;
import com.rest.stream.springkafkastream.dto.UserErrorResponse;
import com.rest.stream.springkafkastream.entity.User;
import com.rest.stream.springkafkastream.exception.UserNotFoundException;
import com.rest.stream.springkafkastream.kafka.KafkaProducer;
import com.rest.stream.springkafkastream.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<InsertUserResponse<User>> insertUser(@Valid @RequestBody InsertUserRequest request) {
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

    @GetMapping
    public ResponseEntity<FindUsersResponse> findUsers() {
        List<User> userData = userService.getAllUsers();
        kafkaProducer.sendBatchMessage(userData);

        FindUsersResponse response = new FindUsersResponse(
                HttpStatus.OK.value(),
                "Success",
                "Users found successfully",
                userData
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{USER_ID}")
    public ResponseEntity<FindUserResponse<User>> findUserById(@PathVariable UUID USER_ID) {
        User userData = userService.getUserById(USER_ID);
        kafkaProducer.sendMessage(userData);

        FindUserResponse<User> response  = new FindUserResponse<>(
                HttpStatus.OK.value(),
                "Success",
                "User found successfully",
                userData
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
}
