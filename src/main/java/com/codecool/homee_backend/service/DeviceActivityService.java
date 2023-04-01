package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.useractivity.DeviceActivityDto;
import com.codecool.homee_backend.entity.DeviceActivity;
import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.mapper.DeviceActivityMapper;
import com.codecool.homee_backend.repository.DeviceActivityRepository;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import com.codecool.homee_backend.service.exception.ActivityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceActivityService {
    private final DeviceActivityRepository deviceActivityRepository;
    private final HomeeUserRepository homeeUserRepository;
    private final DeviceActivityMapper deviceActivityMapper;

    public DeviceActivityService(DeviceActivityRepository deviceActivityRepository, HomeeUserRepository homeeUserRepository, DeviceActivityMapper deviceActivityMapper) {
        this.deviceActivityRepository = deviceActivityRepository;
        this.homeeUserRepository = homeeUserRepository;
        this.deviceActivityMapper = deviceActivityMapper;
    }

    public List<DeviceActivityDto> getActivitiesForDevice(UUID id) {
        return deviceActivityRepository.findAllByDeviceId(id).stream()
                .map(deviceActivityMapper::mapUserActivityEntityToDto)
                .toList();
    }

    public List<DeviceActivityDto> getUserDevicesActivity(UUID userId) {
        HomeeUser homeeUser = homeeUserRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return homeeUser.getSpaces().stream()
                .flatMap(space -> space.getDevices().stream())
                .flatMap(device -> device.getDeviceActivities().stream())
                .map(deviceActivityMapper::mapUserActivityEntityToDto)
                .toList();
    }

    public DeviceActivityDto getActivity(UUID id) {
        return deviceActivityRepository.findById(id)
                .map(deviceActivityMapper::mapUserActivityEntityToDto)
                .orElseThrow(() -> new ActivityNotFoundException(id));
    }

    public void addNewActivity(DeviceActivity deviceActivity) {
        deviceActivityRepository.save(deviceActivity);
    }


}
