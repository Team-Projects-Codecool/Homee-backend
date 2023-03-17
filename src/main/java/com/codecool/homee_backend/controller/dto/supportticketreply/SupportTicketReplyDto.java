package com.codecool.homee_backend.controller.dto.supportticketreply;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record SupportTicketReplyDto(
        UUID id,
        String description,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime creationTime
) {
}
