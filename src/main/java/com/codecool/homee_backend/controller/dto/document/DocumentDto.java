package com.codecool.homee_backend.controller.dto.document;

import java.util.UUID;

public record DocumentDto(
        UUID id,
        String name
) {
}
