package com.codecool.homee_backend.mapper;

import com.codecool.homee_backend.controller.dto.device.DeviceDto;
import com.codecool.homee_backend.controller.dto.device.NewDeviceDto;
import com.codecool.homee_backend.entity.Device;
import com.codecool.homee_backend.entity.type.DeviceType;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapper {
    public DeviceDto mapDeviceEntityToDto(Device entity) {
        return new DeviceDto(
                entity.getId(),
                entity.getName(),
                entity.getModel(),
                entity.getDeviceType().toString(),
                entity.getSpot(),
                entity.getWarrantyStart(),
                entity.getWarrantyEnd(),
                entity.getPurchaseDate(),
                entity.getPurchasePrice(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getAbout(),
                entity.getImageName()
        );
    }

    public Device mapDeviceDtoToEntity(NewDeviceDto dto) {
        return new Device(
                dto.name(),
                dto.model(),
                DeviceType.valueOf(dto.deviceType()),
                dto.spot(),
                dto.warrantyStart(),
                dto.warrantyEnd(),
                dto.purchaseDate(),
                dto.purchasePrice(),
                dto.about()
        );
    }
}
