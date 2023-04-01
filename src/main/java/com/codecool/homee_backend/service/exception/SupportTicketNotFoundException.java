package com.codecool.homee_backend.service.exception;

import java.util.UUID;

public class SupportTicketNotFoundException extends ResourcesNotFoundException {

    public SupportTicketNotFoundException(UUID id) { super("Support ticket with id " + id + " not found."); }

}
