package com.codecool.homee_backend.controller.dto.space;

import java.util.UUID;

public record UpdatedSpaceDto(
        UUID id,
        String name,
        String about
        ) {
}
