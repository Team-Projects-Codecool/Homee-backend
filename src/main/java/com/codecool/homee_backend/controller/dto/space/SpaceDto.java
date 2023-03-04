package com.codecool.homee_backend.controller.dto.space;

import com.codecool.homee_backend.controller.dto.device.DeviceDto;

import java.util.List;
import java.util.UUID;

public record SpaceDto(
    UUID id,
    String name,
    String about,
    List<DeviceDto> devices
) {
}
