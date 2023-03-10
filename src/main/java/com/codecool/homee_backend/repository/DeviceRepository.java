package com.codecool.homee_backend.repository;

import com.codecool.homee_backend.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {

    @Query("SELECT DISTINCT d FROM Device d LEFT JOIN FETCH d.notes WHERE d.id=:id")
    List<Device> findAllBySpaceId(UUID id);

    @Transactional
    void deleteDeviceById(UUID deviceId);

    @Modifying
    @Query("DELETE FROM Device d WHERE d.space.id=:spaceId")
    void deleteAllBySpaceId(UUID spaceId);
}
