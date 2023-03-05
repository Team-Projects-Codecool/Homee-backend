package com.codecool.homee_backend.controller;

import com.codecool.homee_backend.controller.dto.device.DeviceDto;
import com.codecool.homee_backend.controller.dto.device.NewDeviceDto;
import com.codecool.homee_backend.service.DeviceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<DeviceDto> getAllDevices() { return deviceService.getAllDevices(); }

    @GetMapping("/{id}")
    public DeviceDto getDevice(@PathVariable UUID id) { return deviceService.getDevice(id); }

    @PostMapping
    public DeviceDto addNewDevice(@RequestBody NewDeviceDto newDevice) {
        return deviceService.addNewDevice(newDevice);
    }

    @PutMapping("/{deviceId}/spaces/{spaceId}")
    public void assignDeviceToSpace(@PathVariable UUID deviceId, @PathVariable UUID spaceId) {
        deviceService.assignDeviceToSpace(deviceId, spaceId);
    }
}
