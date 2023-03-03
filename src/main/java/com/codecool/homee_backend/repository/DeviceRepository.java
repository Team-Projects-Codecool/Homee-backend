package com.codecool.homee_backend.repository;

import com.codecool.homee_backend.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findDevicesBySpaceId(Long id);
}
