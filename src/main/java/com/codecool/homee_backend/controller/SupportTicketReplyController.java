package com.codecool.homee_backend.controller;

import com.codecool.homee_backend.controller.dto.supportticketreply.NewSupportTicketReplyDto;
import com.codecool.homee_backend.controller.dto.supportticketreply.SupportTicketReplyDto;
import com.codecool.homee_backend.service.SupportTicketReplyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

import static com.codecool.homee_backend.config.auth.SpringSecurityConfig.ADMIN;

@RolesAllowed(ADMIN)
@RestController
@RequestMapping("/api/v1/support-ticket-replies")
public class SupportTicketReplyController {

    private final SupportTicketReplyService supportTicketReplyService;

    public SupportTicketReplyController(SupportTicketReplyService supportTicketReplyService) {
        this.supportTicketReplyService = supportTicketReplyService;
    }

    @GetMapping(params = "ticketId")
    public List<SupportTicketReplyDto> getRepliesForTicket(@RequestParam UUID ticketId) {
        return supportTicketReplyService.getRepliesForTicket(ticketId);
    }

    @PostMapping
    public SupportTicketReplyDto addNewReply(@RequestBody NewSupportTicketReplyDto newReply) {
        return supportTicketReplyService.addNewReply(newReply);
    }

    @DeleteMapping("/{id}")
    public void deleteReply(@PathVariable UUID id) {
         supportTicketReplyService.deleteReply(id);
    }
}
