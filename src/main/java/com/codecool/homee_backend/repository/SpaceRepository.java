package com.codecool.homee_backend.repository;

import com.codecool.homee_backend.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpaceRepository extends JpaRepository<Space, UUID> {

    @Query("SELECT DISTINCT s FROM Space s LEFT JOIN FETCH s.devices")
    List<Space> findAllBy();

    @Query("SELECT DISTINCT s FROM Space s JOIN FETCH s.homeeUsers u LEFT JOIN FETCH s.devices WHERE u.id = :homeeUserId")
    List<Space> findByHomeeUserId(UUID homeeUserId);

    @Query("SELECT DISTINCT s FROM Space s JOIN FETCH s.spaceGroup sg LEFT JOIN FETCH s.devices WHERE sg.id = :groupId")
    List<Space> findByGroupId(UUID groupId);

}
