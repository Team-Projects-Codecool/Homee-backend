package com.codecool.homee_backend.controller.dto.homeeuser;

import java.util.UUID;

public record AuthenticatedUserDto(
        UUID id,
        String username,
        String token
) {
}
