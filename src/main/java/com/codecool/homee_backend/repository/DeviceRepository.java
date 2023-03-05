package com.codecool.homee_backend.repository;

import com.codecool.homee_backend.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Device findDevicesBySpaceId(UUID id);
}
