package com.codecool.homee_backend.controller;

import com.codecool.homee_backend.controller.dto.supportticket.NewSupportTicketDto;
import com.codecool.homee_backend.controller.dto.supportticket.SupportTicketDto;
import com.codecool.homee_backend.service.SupportTicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/support-tickets")
public class SupportTicketController {

    private final SupportTicketService supportTicketService;

    public SupportTicketController(SupportTicketService supportTicketService) {
        this.supportTicketService = supportTicketService;
    }

    @GetMapping
    public List<SupportTicketDto> getAllSupportTickets() {
        return supportTicketService.getAllSupportTickets();
    }

    @PostMapping
    public SupportTicketDto addNewSupportTicket(@RequestBody NewSupportTicketDto newSupportTicket) {
        return supportTicketService.addNewSupportTicket(newSupportTicket);
    }

    @GetMapping(params = "userId")
    public List<SupportTicketDto> getAllSupportTicketsForUser(@RequestParam UUID userId) {
        return supportTicketService.getAllSupportTicketsForHomeeUser(userId);
    }

    @GetMapping("/{id}")
    public SupportTicketDto getSupportTicket(@PathVariable UUID id) {
        return supportTicketService.getSupportTicket(id);
    }


    @PutMapping("/{id}")
    public SupportTicketDto closeSupportTicket(@PathVariable UUID id) {
        return supportTicketService.closeSupportTicket(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSupportTicket(@PathVariable UUID id) {
        supportTicketService.deleteSupportTicket(id);
    }
}
