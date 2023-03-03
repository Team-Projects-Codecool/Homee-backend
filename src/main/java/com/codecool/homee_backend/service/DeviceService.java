package com.codecool.homee_backend.service;

import com.codecool.homee_backend.entity.Device;
import com.codecool.homee_backend.repository.DeviceRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {this.deviceRepository = deviceRepository;};

    public void addDevice(Device device) {deviceRepository.save(device);}

}
