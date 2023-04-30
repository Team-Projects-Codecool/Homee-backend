package com.codecool.homee_backend.controller;

import com.codecool.homee_backend.controller.dto.notification.NewNotificationDto;
import com.codecool.homee_backend.controller.dto.notification.NotificationDto;
import com.codecool.homee_backend.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

import static com.codecool.homee_backend.config.auth.SpringSecurityConfig.USER;

@RolesAllowed(USER)
@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{id}")
    public NotificationDto getNotification(@PathVariable UUID id) {
        return notificationService.getNotification(id);
    }

    @GetMapping(params = "userId")
    public List<NotificationDto> getUserNotifications(@RequestParam UUID userId) {
        return notificationService.getUserNotifications(userId);
    }

    @PutMapping(value = "/{id}", params = "read")
    public void markAsRead(@PathVariable UUID id) {
        notificationService.markAsRead(id);
    }

    @PostMapping
    public NotificationDto addNewNotification(@RequestBody NewNotificationDto dto) {
        return notificationService.addNewNotification(dto);
    }

}
