package com.codecool.homee_backend.controller.dto.notification;

import java.util.UUID;

public record NewNotificationDto(
        UUID userId,
        String message
) {}
