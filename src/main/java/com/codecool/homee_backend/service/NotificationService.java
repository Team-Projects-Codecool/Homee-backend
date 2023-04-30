package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.notification.NewNotificationDto;
import com.codecool.homee_backend.controller.dto.notification.NotificationDto;
import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.entity.Notification;
import com.codecool.homee_backend.mapper.NotificationMapper;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import com.codecool.homee_backend.repository.NotificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final HomeeUserRepository homeeUserRepository;

    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationRepository notificationRepository, HomeeUserRepository homeeUserRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.homeeUserRepository = homeeUserRepository;
        this.notificationMapper = notificationMapper;
    }

    public NotificationDto getNotification(UUID id) {
        return notificationRepository.findById(id)
                .map(notificationMapper::mapNotificationEntityToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<NotificationDto> getUserNotifications(UUID userId) {
        return notificationRepository.findAllByHomeeUserIdOrderByCreationDate(userId)
                .stream()
                .map(notificationMapper::mapNotificationEntityToDto)
                .toList();
    }

    public void markAsRead(UUID id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    public NotificationDto addNewNotification(NewNotificationDto dto) {
        HomeeUser homeeUser = homeeUserRepository.findById(dto.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        Notification notification = notificationMapper.mapNotificationDtoToEntity(dto);
        notification.setHomeeUser(homeeUser);
        return notificationMapper.mapNotificationEntityToDto(notificationRepository.save(notification));
    }
}
