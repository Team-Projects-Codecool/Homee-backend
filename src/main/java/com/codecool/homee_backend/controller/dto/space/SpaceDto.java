package com.codecool.homee_backend.controller.dto.space;

import com.codecool.homee_backend.entity.Device;

import java.util.List;
import java.util.UUID;

public record SpaceDto(
    UUID id,
    String name,
    String about,
    List<Device> devices
) {
}
