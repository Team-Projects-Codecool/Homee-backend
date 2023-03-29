package com.codecool.homee_backend.controller.dto.device;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdatedDeviceDto(
        UUID id,
        String name,
        String model,
        String deviceType,
        String spot,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime warrantyStart,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime warrantyEnd,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        LocalDateTime purchaseDate,
        Double purchasePrice,
        String about
) {
}
