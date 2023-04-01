package com.codecool.homee_backend.service.exception;

import java.util.UUID;

public class SpaceNotFoundException extends ResourcesNotFoundException {

    public SpaceNotFoundException(UUID id) { super("Space with id " + id + " not found."); }

}
