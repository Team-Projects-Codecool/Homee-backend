package com.codecool.homee_backend.controller.dto.note;

import java.util.UUID;

public record UpdatedNoteDto (
        UUID id,
        String description
) {
}
