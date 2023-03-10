package com.codecool.homee_backend.controller.dto.group;

import java.util.UUID;

public record SpaceGroupDto(
        UUID id,
        String name,
        String about
) {
}
