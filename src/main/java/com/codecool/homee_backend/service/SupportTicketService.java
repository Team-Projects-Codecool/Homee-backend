package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.supportticket.NewSupportTicketDto;
import com.codecool.homee_backend.controller.dto.supportticket.SupportTicketDto;
import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.entity.SupportTicket;
import com.codecool.homee_backend.entity.status.TicketStatus;
import com.codecool.homee_backend.mapper.SupportTicketMapper;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import com.codecool.homee_backend.repository.SupportTicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SupportTicketService {

    private final SupportTicketRepository supportTicketRepository;
    private final HomeeUserRepository homeeUserRepository;
    private final SupportTicketMapper supportTicketMapper;

    public SupportTicketService(SupportTicketRepository supportTicketRepository, HomeeUserRepository homeeUserRepository, SupportTicketMapper supportTicketMapper) {
        this.supportTicketRepository = supportTicketRepository;
        this.homeeUserRepository = homeeUserRepository;
        this.supportTicketMapper = supportTicketMapper;
    }

    public List<SupportTicketDto> getAllSupportTickets() {
        return supportTicketRepository.findAll()
                .stream()
                .map(supportTicketMapper::mapSupportTicketEntityToDto)
                .toList();
    }

    public List<SupportTicketDto> getAllSupportTicketsForHomeeUser(UUID id) {
        return supportTicketRepository.getAllByHomeeUserId(id)
                .stream()
                .map(supportTicketMapper::mapSupportTicketEntityToDto)
                .toList();
    }

    public SupportTicketDto getSupportTicket(UUID id) {
        return supportTicketRepository.findById(id)
                .map(supportTicketMapper::mapSupportTicketEntityToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public SupportTicketDto closeSupportTicket(UUID id) {
        SupportTicket supportTicket = supportTicketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        supportTicket.setTicketStatus(TicketStatus.CLOSED);
        supportTicket.setClosingTime(LocalDateTime.now());
        SupportTicket saved = supportTicketRepository.save(supportTicket);
        return supportTicketMapper.mapSupportTicketEntityToDto(saved);
    }

    public void deleteSupportTicket(UUID id) {
        supportTicketRepository.deleteById(id);
    }

    public SupportTicketDto addNewSupportTicket(NewSupportTicketDto newSupportTicket) {
        HomeeUser homeeUser = homeeUserRepository.findById(newSupportTicket.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        SupportTicket supportTicket = supportTicketMapper.mapSupportTicketDtoToEntity(newSupportTicket);
        supportTicket.setHomeeUser(homeeUser);
        SupportTicket supportTicketDb = supportTicketRepository.save(supportTicket);
        return supportTicketMapper.mapSupportTicketEntityToDto(supportTicketDb);
    }
}
