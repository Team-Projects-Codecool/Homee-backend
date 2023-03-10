package com.codecool.homee_backend.mapper;

import com.codecool.homee_backend.controller.dto.document.DocumentDto;
import com.codecool.homee_backend.controller.dto.document.NewDocumentDto;
import com.codecool.homee_backend.entity.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public DocumentDto mapDocumentEntityToDto(Document entity) {
        return new DocumentDto(
                entity.getId(),
                entity.getName()
        );
    }

    public Document mapDocumentDtoToEntity(NewDocumentDto dto) {
        return new Document(
                dto.name()
        );
    }

}
