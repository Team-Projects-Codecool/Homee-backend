package com.codecool.homee_backend.mapper;


import com.codecool.homee_backend.controller.dto.supportticket.NewSupportTicketDto;
import com.codecool.homee_backend.controller.dto.supportticket.SupportTicketDto;
import com.codecool.homee_backend.entity.SupportTicket;
import org.springframework.stereotype.Component;

@Component
public class SupportTicketMapper {

    public SupportTicket mapSupportTicketDtoToEntity(NewSupportTicketDto dto) {
        return new SupportTicket(
                dto.title(),
                dto.description()
        );
    }

    public SupportTicketDto mapSupportTicketEntityToDto(SupportTicket entity) {
        return new SupportTicketDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getTicketStatus(),
                entity.getCreationTime(),
                entity.getClosingTime()
        );
    }

}
