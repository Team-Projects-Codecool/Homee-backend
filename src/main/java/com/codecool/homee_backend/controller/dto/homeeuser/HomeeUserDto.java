package com.codecool.homee_backend.controller.dto.homeeuser;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record HomeeUserDto(
        UUID id,
        String username,
        String email,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime registeredTime,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime lastLoggedIn,
        String firstName,
        String lastName,
        String about
) {
}
