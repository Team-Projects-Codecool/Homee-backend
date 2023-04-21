package com.codecool.homee_backend.controller;

import com.codecool.homee_backend.controller.dto.device.DeviceDto;
import com.codecool.homee_backend.controller.dto.device.NewDeviceDto;
import com.codecool.homee_backend.controller.dto.device.UpdatedDeviceDto;
import com.codecool.homee_backend.entity.type.DeviceType;
import com.codecool.homee_backend.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.codecool.homee_backend.config.auth.SpringSecurityConfig.USER;

@RolesAllowed(USER)
@RestController
@RequestMapping("/api/v1/devices")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<DeviceDto> getAllDevices() { return deviceService.getAllDevices(); }

    @GetMapping("/{id}")
    public DeviceDto getDevice(@PathVariable UUID id) { return deviceService.getDevice(id); }

    @PutMapping("/{id}")
    public DeviceDto updateDevice(@RequestBody UpdatedDeviceDto dto) { return deviceService.updateDevice(dto); }

    @GetMapping("/types")
    public List<DeviceType> getDeviceTypes() { return deviceService.getTypes(); }

    @GetMapping(params = "spaceId")
    public List<DeviceDto> getDevicesForSpace(@RequestParam UUID spaceId) {
        return deviceService.getDevicesForSpace(spaceId);
    }

    @GetMapping(params = "userId")
    public List<DeviceDto> getDevicesForUser(@RequestParam UUID userId) {
        return deviceService.getDevicesForUser(userId);
    }

    @GetMapping(params = {"userId", "count"})
    public Integer getAmountOfUserDevices(@RequestParam UUID userId) {
        return deviceService.countUserDevices(userId);
    }


    @PostMapping
    public DeviceDto addNewDevice(@RequestBody NewDeviceDto newDevice) {
        return deviceService.addNewDevice(newDevice);
    }

    @PostMapping(params = {"image"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadDeviceImage(@RequestParam("file") MultipartFile file, @RequestParam("deviceId") String deviceId) throws IOException {
        deviceService.changeDeviceImage(file, deviceId);
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
