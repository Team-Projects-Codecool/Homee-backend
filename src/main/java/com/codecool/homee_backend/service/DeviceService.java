package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.device.DeviceDto;
import com.codecool.homee_backend.controller.dto.device.NewDeviceDto;
import com.codecool.homee_backend.controller.dto.device.UpdatedDeviceDto;
import com.codecool.homee_backend.entity.Device;
import com.codecool.homee_backend.entity.DeviceActivity;
import com.codecool.homee_backend.entity.Space;
import com.codecool.homee_backend.entity.type.ActivityType;
import com.codecool.homee_backend.entity.type.DeviceType;
import com.codecool.homee_backend.mapper.DeviceMapper;
import com.codecool.homee_backend.repository.DeviceRepository;
import com.codecool.homee_backend.repository.SpaceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

    public List<DeviceDto> getDevicesForUser(UUID id) {
        return deviceRepository.findAllByUserId(id).stream()
                .map(deviceMapper::mapDeviceEntityToDto)
                .toList();
    }

    public DeviceDto addNewDevice(NewDeviceDto dto) {
        Device device = deviceMapper.mapDeviceDtoToEntity(dto);
        addCreatedDeviceActivity(device);
        Device deviceDb = deviceRepository.save(device);
        return deviceMapper.mapDeviceEntityToDto(deviceDb);
    }


    public void assignDeviceToSpace(UUID deviceId, UUID spaceId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        device.setSpace(space);
        addAssignedSpaceActivity(device, space);
        deviceRepository.save(device);
    }

    public void deleteDevice(UUID deviceId) {
        Device device = deviceRepository.findById(deviceId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        device.setSpace(null);
        device.getDeviceActivities().forEach(a -> {
            a.setDevice(null);
        });
        deviceRepository.save(device);
        deviceRepository.deleteDeviceById(deviceId);
    }

    private void addCreatedDeviceActivity(Device device) {
        DeviceActivity deviceActivity = new DeviceActivity(
                device,
                createOnNewDeviceDescription(device),
                ActivityType.INFORMATION
        );
        device.addActivity(deviceActivity);
    }

    private void addAssignedSpaceActivity(Device device, Space space) {
        DeviceActivity deviceActivity = new DeviceActivity(
                device,
                createAssignSpaceDescription(space),
                ActivityType.INFORMATION
        );

        device.addActivity(deviceActivity);
    }

    private String createOnNewDeviceDescription(Device device) {
        return "Device " + device.getName() + " has been created";
    }
    private String createAssignSpaceDescription(Space space) {
        return "Device has been assigned to " + space.getName() + " space.";
    }

    public Integer countUserDevices(UUID userId) {
        return deviceRepository.findAllByUserId(userId).size();
    }

    public List<DeviceType> getTypes() {
        return List.of(DeviceType.values());
    }

    public DeviceDto updateDevice(UpdatedDeviceDto dto) {
        Device device = deviceRepository.findById(dto.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        device.setName(dto.name());
        device.setModel(dto.model());
        device.setDeviceType(DeviceType.valueOf(dto.deviceType()));
        device.setAbout(dto.spot());
        device.setWarrantyStart(dto.warrantyStart());
        device.setWarrantyEnd(dto.warrantyEnd());
        device.setPurchaseDate(dto.purchaseDate());
        device.setAbout(dto.about());
        device.setUpdatedAt(LocalDateTime.now());
        deviceRepository.save(device);
        return deviceMapper.mapDeviceEntityToDto(device);
    }
}
