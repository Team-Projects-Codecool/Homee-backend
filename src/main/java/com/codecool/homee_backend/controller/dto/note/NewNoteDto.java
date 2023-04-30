package com.codecool.homee_backend.controller.dto.note;

import java.util.UUID;

public record NewNoteDto(
        UUID deviceId,
        String title,
        String description
) {
}
