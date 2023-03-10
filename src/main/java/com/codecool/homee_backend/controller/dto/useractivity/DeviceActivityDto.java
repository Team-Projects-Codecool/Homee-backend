package com.codecool.homee_backend.controller.dto.useractivity;

import com.codecool.homee_backend.entity.type.ActivityType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeviceActivityDto(
        UUID id,
        String description,
        String deviceName,
        ActivityType activityType,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime date
) {
}
