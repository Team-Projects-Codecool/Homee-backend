package com.codecool.homee_backend.controller.dto.homeeuser;

import com.codecool.homee_backend.controller.dto.space.SpaceDto;
import com.codecool.homee_backend.entity.type.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record HomeeUserDto(
        UUID id,
        String username,
        String email,
        String password,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime registeredTime,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime lastLoggedIn,
        String firstName,
        String lastName,
        String about,
        List<SpaceDto> spaces,
        UserRole userRole
) {
}
