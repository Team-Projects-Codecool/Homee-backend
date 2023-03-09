package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.device.DeviceDto;
import com.codecool.homee_backend.controller.dto.device.NewDeviceDto;
import com.codecool.homee_backend.entity.Device;
import com.codecool.homee_backend.entity.DeviceActivity;
import com.codecool.homee_backend.entity.Space;
import com.codecool.homee_backend.entity.type.ActivityType;
import com.codecool.homee_backend.mapper.DeviceMapper;
import com.codecool.homee_backend.repository.DeviceRepository;
import com.codecool.homee_backend.repository.SpaceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final SpaceRepository spaceRepository;
    private final DeviceMapper deviceMapper;


    public DeviceService(DeviceRepository deviceRepository, SpaceRepository spaceRepository, DeviceMapper deviceMapper) {this.deviceRepository = deviceRepository;
        this.spaceRepository = spaceRepository;
        this.deviceMapper = deviceMapper;
    }

    public List<DeviceDto> getAllDevices() {
        return deviceRepository.findAll().stream()
                .map(deviceMapper::mapDeviceEntityToDto)
                .toList();
    }

    public DeviceDto getDevice(UUID id) {
        return deviceRepository.findById(id)
                .map(deviceMapper::mapDeviceEntityToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<DeviceDto> getDevicesForSpace(UUID id) {
        return deviceRepository.findAllBySpaceId(id).stream()
                .map(deviceMapper::mapDeviceEntityToDto)
                .toList();
    }

    public DeviceDto addNewDevice(NewDeviceDto dto) {
        Device device = deviceMapper.mapDeviceDtoToEntity(dto);
        Device deviceDb = deviceRepository.save(device);
        return deviceMapper.mapDeviceEntityToDto(deviceDb);
    }


    public void assignDeviceToSpace(UUID deviceId, UUID spaceId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        device.setSpace(space);
        space.addDevice(device);

        DeviceActivity deviceActivity = new DeviceActivity(
                device,
                createAssignDeviceDescription(space),
                ActivityType.INFORMATION
        );

        device.addActivity(deviceActivity);

        deviceRepository.save(device);
        spaceRepository.save(space);
    }

    public void deleteDevice(UUID deviceId) {
        deviceRepository.deleteDeviceById(deviceId);
    }

    private String createAssignDeviceDescription(Space space) {
        return "Device has been assigned to " + space.getName() + " space.";
    }
}
