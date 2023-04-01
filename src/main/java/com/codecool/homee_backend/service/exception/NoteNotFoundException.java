package com.codecool.homee_backend.service.exception;

import java.util.UUID;

public class NoteNotFoundException extends ResourcesNotFoundException {

    public NoteNotFoundException(UUID id) { super("Note with id " + id + " not found."); }

}
