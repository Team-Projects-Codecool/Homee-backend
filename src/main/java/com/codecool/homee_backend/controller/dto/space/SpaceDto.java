package com.codecool.homee_backend.controller.dto.space;

import java.util.UUID;

public record SpaceDto(
    UUID id,
    UUID ownerId,
    String name,
    String about
) {
}
