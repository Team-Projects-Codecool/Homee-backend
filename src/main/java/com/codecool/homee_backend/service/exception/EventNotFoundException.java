package com.codecool.homee_backend.service.exception;

import java.util.UUID;

public class EventNotFoundException extends ResourcesNotFoundException {

    public EventNotFoundException(UUID id) { super("Event with id " + id + " not found."); }

}
