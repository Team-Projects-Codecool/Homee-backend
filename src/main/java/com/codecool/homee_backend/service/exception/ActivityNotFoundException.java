package com.codecool.homee_backend.service.exception;

import java.util.UUID;

public class ActivityNotFoundException extends ResourcesNotFoundException {

    public ActivityNotFoundException(UUID id) { super("Activity with id " + id + " not found."); }

}
