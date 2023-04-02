package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.supportticket.NewSupportTicketDto;
import com.codecool.homee_backend.controller.dto.supportticket.SupportTicketDto;
import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.entity.SupportTicket;
import com.codecool.homee_backend.entity.status.TicketStatus;
import com.codecool.homee_backend.mapper.SupportTicketMapper;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import com.codecool.homee_backend.repository.SupportTicketRepository;
import com.codecool.homee_backend.service.exception.HomeeUserNotFoundException;
import com.codecool.homee_backend.service.exception.SupportTicketNotFoundException;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new SupportTicketNotFoundException(id));
    }

    public SupportTicketDto closeSupportTicket(UUID id) {
        SupportTicket supportTicket = supportTicketRepository.findById(id)
                .orElseThrow(() -> new SupportTicketNotFoundException(id));
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
                .orElseThrow(() -> new HomeeUserNotFoundException(newSupportTicket.userId()));
        SupportTicket supportTicket = supportTicketMapper.mapSupportTicketDtoToEntity(newSupportTicket);
        supportTicket.setHomeeUser(homeeUser);
        SupportTicket supportTicketDb = supportTicketRepository.save(supportTicket);
        return supportTicketMapper.mapSupportTicketEntityToDto(supportTicketDb);
    }
}
