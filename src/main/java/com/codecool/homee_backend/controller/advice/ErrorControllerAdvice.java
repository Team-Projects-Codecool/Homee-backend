package com.codecool.homee_backend.controller.advice;

import com.codecool.homee_backend.service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(value = {ActivityNotFoundException.class,
            DeviceNotFoundException.class,
            SpaceNotFoundException.class,
            DocumentNotFoundException.class,
            EventNotFoundException.class,
            HomeeUserNotFoundException.class,
            NoteNotFoundException.class,
            SpaceNotFoundException.class,
            SupportTicketReplyNotFoundException.class,
            SupportTicketNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleNoResources(ResourcesNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }



    private record ErrorResponse(
            String errorMessage
    ){
    }

}
