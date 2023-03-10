package com.codecool.homee_backend.controller;

import com.codecool.homee_backend.controller.dto.event.EventDto;
import com.codecool.homee_backend.controller.dto.event.NewEventDto;
import com.codecool.homee_backend.controller.dto.event.UpdatedEvent;
import com.codecool.homee_backend.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@PathVariable UUID id) {
        return eventService.getSingleEvent(id);
    }

    @GetMapping(params = "deviceId")
    public List<EventDto> getEventsForDevice(@RequestParam UUID deviceId) {
        return eventService.getEventsForDevice(deviceId);
    }

    @PostMapping
    public EventDto addNewEvent(@RequestBody NewEventDto newEvent) {
        return eventService.addNewEvent(newEvent);
    }

    @PutMapping
    public EventDto updateEvent(@RequestBody UpdatedEvent updatedEvent) {
        return eventService.updateEvent(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable UUID id) {
        eventService.deleteEvent(id);
    }

}
