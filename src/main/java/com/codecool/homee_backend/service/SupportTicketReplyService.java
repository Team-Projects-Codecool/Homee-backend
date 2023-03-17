package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.supportticketreply.NewSupportTicketReplyDto;
import com.codecool.homee_backend.controller.dto.supportticketreply.SupportTicketReplyDto;
import com.codecool.homee_backend.entity.SupportTicket;
import com.codecool.homee_backend.entity.SupportTicketReply;
import com.codecool.homee_backend.mapper.SupportTicketReplyMapper;
import com.codecool.homee_backend.repository.SupportTicketReplyRepository;
import com.codecool.homee_backend.repository.SupportTicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class SupportTicketReplyService {

    private final SupportTicketReplyRepository supportTicketReplyRepository;

    private final SupportTicketRepository supportTicketRepository;
    private final SupportTicketReplyMapper supportTicketReplyMapper;

    public SupportTicketReplyService(SupportTicketReplyRepository supportTicketReplyRepository, SupportTicketRepository supportTicketRepository, SupportTicketReplyMapper supportTicketReplyMapper) {
        this.supportTicketReplyRepository = supportTicketReplyRepository;
        this.supportTicketRepository = supportTicketRepository;
        this.supportTicketReplyMapper = supportTicketReplyMapper;
    }

    public List<SupportTicketReplyDto> getRepliesForTicket(UUID ticketId) {
        return supportTicketReplyRepository.findAllBySupportTicketId(ticketId).stream()
                .map(supportTicketReplyMapper::mapSupportTicketReplyEntityToDto)
                .toList();
    }

    public void deleteReply(UUID id) {
        supportTicketReplyRepository.deleteById(id);
    }

    public SupportTicketReplyDto addNewReply(NewSupportTicketReplyDto newReply) {
        SupportTicket supportTicket = supportTicketRepository.findById(newReply.supportTicketId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        SupportTicketReply supportTicketReply = supportTicketReplyMapper.mapSupportTicketReplyDtoToEntity(newReply);
        supportTicketReply.setSupportTicket(supportTicket);
        SupportTicketReply supportTicketReplyDb = supportTicketReplyRepository.save(supportTicketReply);
        return supportTicketReplyMapper.mapSupportTicketReplyEntityToDto(supportTicketReplyDb);
    }
}
