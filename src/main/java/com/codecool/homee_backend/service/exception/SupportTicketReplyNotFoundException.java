package com.codecool.homee_backend.service.exception;

import java.util.UUID;

public class SupportTicketReplyNotFoundException extends ResourcesNotFoundException {

    public SupportTicketReplyNotFoundException(UUID id) { super("Support ticket reply with id " + id + " not found."); }

}
