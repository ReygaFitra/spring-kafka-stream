package com.rest.stream.springkafkastream.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @Column(updatable = false, nullable = false)
    private UUID USER_ID;

    @Column(nullable = false, length = 100)
    private String FULL_NAME;

    @Column(nullable = false, length = 400)
    private String ADDRESS;

    @Column(nullable = false, length = 50, unique = true)
    private String EMAIL;

    @Column(nullable = false, length = 20, unique = true)
    private String PHONE_NUMBER;
}
