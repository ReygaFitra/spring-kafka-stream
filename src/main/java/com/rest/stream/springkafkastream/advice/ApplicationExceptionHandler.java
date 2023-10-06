package com.rest.stream.springkafkastream.advice;

import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rest.stream.springkafkastream.dto.UserErrorResponse;
import com.rest.stream.springkafkastream.exception.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        });
    
        Map<String, Object> errorResponseMap = new HashMap<>(); 
        errorResponseMap.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponseMap.put("message", "Bad Request");
        errorResponseMap.put("errors", errorMap);
    
        return new ResponseEntity<>(errorResponseMap, HttpStatus.BAD_REQUEST);
    }
    


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserErrorResponse> handleUserNotFoundException(Exception exception) {
        UserErrorResponse errorResponse = new UserErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "User not found",
            exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
