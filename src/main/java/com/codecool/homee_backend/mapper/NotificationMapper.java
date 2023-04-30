package com.codecool.homee_backend.mapper;

import com.codecool.homee_backend.controller.dto.notification.NewNotificationDto;
import com.codecool.homee_backend.controller.dto.notification.NotificationDto;
import com.codecool.homee_backend.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationDto mapNotificationEntityToDto(Notification entity) {
        return new NotificationDto(
                entity.getId(),
                entity.getCreationDate(),
                entity.getMessage(),
                entity.getRead()
        );
    }

    public Notification mapNotificationDtoToEntity(NewNotificationDto dto) {
        return new Notification(
                dto.message()
        );
    }

}
