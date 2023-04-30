package com.codecool.homee_backend.repository;

import com.codecool.homee_backend.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findAllByHomeeUserIdOrderByCreationDate(UUID id);

}
