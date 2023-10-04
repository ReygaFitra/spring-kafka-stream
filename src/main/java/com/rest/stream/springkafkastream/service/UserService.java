package com.rest.stream.springkafkastream.service;

import com.rest.stream.springkafkastream.dto.InsertUserRequest;
import com.rest.stream.springkafkastream.entity.User;
import com.rest.stream.springkafkastream.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(InsertUserRequest request) {
        User userData = new User();
        userData.setFULL_NAME(request.getFullName());
        userData.setADDRESS(request.getAddress());
        userData.setEMAIL(request.getEmail());
        userData.setPHONE_NUMBER(request.getPhoneNumber());

        return userRepository.save(userData);
    }
}
