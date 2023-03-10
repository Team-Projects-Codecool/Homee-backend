package com.codecool.homee_backend.mapper;

import com.codecool.homee_backend.controller.dto.event.EventDto;
import com.codecool.homee_backend.controller.dto.event.NewEventDto;
import com.codecool.homee_backend.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventDto mapEventEntityToDto(Event entity) {
        return new EventDto(
                entity.getId(),
                entity.getName(),
                entity.getEventType(),
                entity.getNotification(),
                entity.getScheduledAt()
        );
    }

    public Event mapEventDtoToEntity(NewEventDto dto) {
        return new Event(
                dto.name(),
                dto.eventType(),
                dto.notification(),
                dto.scheduledAt()
        );
    }

}
