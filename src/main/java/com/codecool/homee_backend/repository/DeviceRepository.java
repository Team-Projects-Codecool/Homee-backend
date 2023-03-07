package com.codecool.homee_backend.repository;

import com.codecool.homee_backend.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {

    @Query("SELECT d FROM Device d WHERE d.space=:id")
    List<Device> findDevicesBySpaceId(UUID id);
}
