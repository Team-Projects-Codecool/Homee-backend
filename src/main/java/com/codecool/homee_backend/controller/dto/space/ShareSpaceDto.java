package com.codecool.homee_backend.controller.dto.space;

import java.util.UUID;

public record ShareSpaceDto(
        UUID spaceId,
        String invitationEmail
) {
}
