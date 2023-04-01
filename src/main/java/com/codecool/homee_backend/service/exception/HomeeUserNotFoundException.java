package com.codecool.homee_backend.service.exception;

import java.util.UUID;

public class HomeeUserNotFoundException extends ResourcesNotFoundException {

    public HomeeUserNotFoundException(UUID id) { super("Homee user with id " + id + " not found."); }

}
