package com.codecool.homee_backend.service.exception;

public abstract class ResourcesNotFoundException extends RuntimeException {

    public ResourcesNotFoundException(String message) {
        super(message);
    }
}
