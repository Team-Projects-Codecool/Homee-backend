package com.codecool.homee_backend.service.exception;

import java.util.UUID;

public class DocumentNotFoundException extends ResourcesNotFoundException {

    public DocumentNotFoundException(UUID id) { super("Document with id " + id + " not found."); }

}
