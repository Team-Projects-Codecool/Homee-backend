package com.codecool.homee_backend.controller.dto.homeeuser;

import java.util.UUID;

public record UpdatedHomeeUserDto(
        UUID id,
        String username,
        String email,
        String firstName,
        String lastName,
        String about
) {
}
