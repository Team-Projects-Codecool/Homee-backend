package com.codecool.homee_backend.repository;

import com.codecool.homee_backend.entity.SpaceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpaceGroupRepository extends JpaRepository<SpaceGroup, UUID> {

    List<SpaceGroup> findSpaceGroupsByHomeeUser_Id(UUID id);
    List<SpaceGroup> findSpaceGroupsByHomeeUser(UUID id);

    @Query("SELECT sg FROM SpaceGroup sg WHERE sg.homeeUser.id=:id")
    List<SpaceGroup> findSpaceGroupsByHomeeUserId(UUID id);
}
