package com.codecool.homee_backend.controller.dto.note;

import java.util.UUID;

public record NoteDto(
        UUID id,
        String description
) {
}
