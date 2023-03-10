package com.codecool.homee_backend.controller.dto.document;

import java.util.UUID;

public record NewDocumentDto(
        UUID deviceId,
        String name
) {
}
