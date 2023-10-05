package com.rest.stream.springkafkastream.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsertUserRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String address;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;
}

