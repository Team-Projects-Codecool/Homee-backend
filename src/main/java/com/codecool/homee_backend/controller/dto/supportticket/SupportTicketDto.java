package com.codecool.homee_backend.controller.dto.supportticket;

import com.codecool.homee_backend.entity.status.TicketStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record SupportTicketDto(
        UUID id,
        String title,
        String description,
        TicketStatus ticketStatus,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime creationTime,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime closingTime
) {
}
