package com.codecool.homee_backend.mapper;

import com.codecool.homee_backend.controller.dto.supportticketreply.NewSupportTicketReplyDto;
import com.codecool.homee_backend.controller.dto.supportticketreply.SupportTicketReplyDto;
import com.codecool.homee_backend.entity.SupportTicketReply;
import org.springframework.stereotype.Component;

@Component
public class SupportTicketReplyMapper {

    public SupportTicketReply mapSupportTicketReplyDtoToEntity(NewSupportTicketReplyDto dto) {
        return new SupportTicketReply(
                dto.description()
        );
    }

    public SupportTicketReplyDto mapSupportTicketReplyEntityToDto(SupportTicketReply entity) {
        return new SupportTicketReplyDto(
                entity.getId(),
                entity.getDescription(),
                entity.getCreationTime()
        );
    }

}
