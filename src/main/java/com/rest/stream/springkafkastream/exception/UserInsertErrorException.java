package com.rest.stream.springkafkastream.exception;

public class UserInsertErrorException extends RuntimeException {
    public UserInsertErrorException(String message) {
        super(message);
    }
}
