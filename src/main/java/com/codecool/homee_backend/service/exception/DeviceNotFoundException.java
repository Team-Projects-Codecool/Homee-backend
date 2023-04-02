package com.codecool.homee_backend.service.exception;

import java.util.UUID;

public class DeviceNotFoundException extends ResourcesNotFoundException {

    public DeviceNotFoundException(UUID id) { super("Device with id " + id + " not found."); }

}
