package com.rest.stream.springkafkastream.service;

import com.rest.stream.springkafkastream.dto.InsertUserRequest;
import com.rest.stream.springkafkastream.entity.User;
import com.rest.stream.springkafkastream.exception.UserInsertErrorException;
import com.rest.stream.springkafkastream.exception.UserNotFoundException;
import com.rest.stream.springkafkastream.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(InsertUserRequest request) {
        if(request.getFullName() == null || request.getAddress() == null || request.getEmail() == null || request.getPhoneNumber() == null) {
            throw new UserInsertErrorException("Following fields are required: fullName, address, email, phoneNumber");
        }

        User userData = new User();

        userData.setFullName(request.getFullName());
        userData.setAddress(request.getAddress());
        userData.setEmail(request.getEmail());
        userData.setPhoneNumber(request.getPhoneNumber());

        return userRepository.save(userData);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
}
