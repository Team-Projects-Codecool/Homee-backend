package com.codecool.homee_backend.controller.dto.supportticketreply;

import java.util.UUID;

public record NewSupportTicketReplyDto(
        UUID supportTicketId,
        String description
) {
}
