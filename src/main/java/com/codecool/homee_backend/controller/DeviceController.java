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

    @GetMapping(params = "spaceId")
    public List<DeviceDto> getDevicesForSpace(@RequestParam UUID spaceId) {
        return deviceService.getDevicesForSpace(spaceId);
    }

    @PostMapping
    public DeviceDto addNewDevice(@RequestBody NewDeviceDto newDevice) {
        return deviceService.addNewDevice(newDevice);
    }


    @PutMapping(params = {"deviceId", "spaceId"})
    public void assignDeviceToSpace(@RequestParam UUID deviceId, @RequestParam UUID spaceId) {
        deviceService.assignDeviceToSpace(deviceId, spaceId);
    }

    @DeleteMapping("/{deviceId}")
    public void deleteDevice(@PathVariable UUID deviceId) {
        deviceService.deleteDevice(deviceId);
    }
}
