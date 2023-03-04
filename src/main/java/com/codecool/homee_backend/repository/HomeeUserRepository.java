package com.codecool.homee_backend.repository;

import com.codecool.homee_backend.entity.HomeeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HomeeUserRepository extends JpaRepository<HomeeUser, UUID> {
    HomeeUser findHomeeUserById(UUID id);
}
