package com.codecool.homee_backend.controller.dto.notification;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationDto(
        UUID id,
        LocalDateTime creationTime,
        String message,
        Boolean read
){}
