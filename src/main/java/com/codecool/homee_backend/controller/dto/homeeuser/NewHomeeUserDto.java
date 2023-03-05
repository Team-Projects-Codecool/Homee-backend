package com.codecool.homee_backend.controller.dto.homeeuser;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record NewHomeeUserDto(
        String username,
        String email,
        String password,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime registeredTime,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime lastLoggedIn,
        String firstName,
        String lastName,
        String about
) {
}
