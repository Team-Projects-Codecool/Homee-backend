package com.codecool.homee_backend.controller;

import com.codecool.homee_backend.controller.dto.useractivity.DeviceActivityDto;
import com.codecool.homee_backend.service.DeviceActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/activities")
@CrossOrigin(origins = "http://localhost:3000")
public class DeviceActivityController {

    private final DeviceActivityService deviceActivityService;

    public DeviceActivityController(DeviceActivityService deviceActivityService) {
        this.deviceActivityService = deviceActivityService;
    }

    @GetMapping(params = {"deviceId"})
    public List<DeviceActivityDto> getActivitiesForDevice(@RequestParam UUID deviceId) {
        return deviceActivityService.getActivitiesForDevice(deviceId);
    }

    @GetMapping(params = "userId")
    public List<DeviceActivityDto> getActivitiesForUserDevices(@RequestParam UUID userId) {
        return deviceActivityService.getUserDevicesActivity(userId);
    }

    @GetMapping("/{id}")
    public DeviceActivityDto getActivity(@PathVariable UUID id) {
        return deviceActivityService.getActivity(id);
    }
}
