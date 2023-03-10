package com.codecool.homee_backend.controller.dto.supportticket;

import java.util.UUID;

public record NewSupportTicketDto(
        UUID userId,
        String title,
        String description
) {
}
