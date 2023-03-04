package com.codecool.homee_backend.repository;

import com.codecool.homee_backend.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpaceRepository extends JpaRepository<Space, UUID> {
    Space findSpaceById(UUID id);

    @Query("SELECT s FROM Space s JOIN s.homeeUsers u WHERE u.id = :homeeUserId")
    List<Space> findByHomeeUserId(@Param("homeeUserId") UUID homeeUserId);
}
