package com.codecool.homee_backend.scheduler;

import com.codecool.homee_backend.controller.dto.event.EventDto;
import com.codecool.homee_backend.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class NotificationScheduler {

    private final EventService eventService;


    public NotificationScheduler(EventService eventService) {
        this.eventService = eventService;
    }


    @Async
    @Scheduled(cron = "0 0 0 * * *")
    public void sendNotifications() {
        log.info("Sending notifications...");
        List<EventDto> eventsForDate = eventService.getEventsForDate(LocalDate.now());
        for (EventDto event : eventsForDate) {
            log.info(event.name());
        }
    }
}
