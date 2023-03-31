package com.codecool.homee_backend.controller.dto.device;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record NewDeviceImageDto(
        UUID deviceId,
        MultipartFile image
) {
}
