package com.codecool.homee_backend.service.exception;

import java.util.UUID;

public class SpaceGroupNotFoundException extends ResourcesNotFoundException {

    public SpaceGroupNotFoundException(UUID id) { super("Space group with id " + id + " not found."); }

}
