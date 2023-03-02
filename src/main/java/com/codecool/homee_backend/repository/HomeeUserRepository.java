package com.codecool.homee_backend.repository;

import com.codecool.homee_backend.entity.HomeeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeeUserRepository extends JpaRepository<HomeeUser, Long> {
    HomeeUser findHomeeUserById(Long id);
}
