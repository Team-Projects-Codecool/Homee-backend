package com.codecool.homee_backend.controller.dto.note;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record NoteDto(
        UUID id,
        String title,
        String description,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime creationTime
) {
}
