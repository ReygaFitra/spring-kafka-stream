package com.rest.stream.springkafkastream.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "FULL_NAME", nullable = false, length = 100)
    private String fullName;

    @Column(name = "ADDRESS", nullable = false, length = 400)
    private String address;

    @Column(name = "EMAIL", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 20, unique = true)
    private String phoneNumber;
}
