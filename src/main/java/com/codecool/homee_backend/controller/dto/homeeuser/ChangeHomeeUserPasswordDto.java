package com.codecool.homee_backend.controller.dto.homeeuser;

import java.util.UUID;

public record ChangeHomeeUserPasswordDto(
        UUID id,
        String oldPassword,
        String newPassword
) {
}
