package com.codecool.homee_backend.mapper;

import com.codecool.homee_backend.controller.dto.useractivity.DeviceActivityDto;
import com.codecool.homee_backend.entity.DeviceActivity;
import org.springframework.stereotype.Component;

@Component
public class DeviceActivityMapper {

    public DeviceActivityDto mapUserActivityEntityToDto(DeviceActivity entity) {
        return new DeviceActivityDto(
                entity.getId(),
                entity.getDescription(),
                entity.getDevice().getName(),
                entity.getActivityType(),
                entity.getDate()
        );
    }

}
