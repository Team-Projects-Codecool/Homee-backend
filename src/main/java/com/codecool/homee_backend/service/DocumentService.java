package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.document.DocumentDto;
import com.codecool.homee_backend.controller.dto.document.NewDocumentDto;
import com.codecool.homee_backend.entity.Device;
import com.codecool.homee_backend.entity.Document;
import com.codecool.homee_backend.mapper.DocumentMapper;
import com.codecool.homee_backend.repository.DeviceRepository;
import com.codecool.homee_backend.repository.DocumentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DeviceRepository deviceRepository;
    private final DocumentMapper documentMapper;

    public DocumentService(DocumentRepository documentRepository, DeviceRepository deviceRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.deviceRepository = deviceRepository;
        this.documentMapper = documentMapper;
    }

    public Document addNewDocument(NewDocumentDto newDocument, String filePath) {
        Device device = deviceRepository.findById(newDocument.deviceId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Document document = documentMapper.mapDocumentDtoToEntity(newDocument);
        document.setDevice(device);
        document.setPath(filePath);
        device.addDocument(document);
        return documentRepository.save(document);
    }

    public DocumentDto getDocument(UUID id) {
        return documentRepository.findById(id)
                .map(documentMapper::mapDocumentEntityToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<DocumentDto> getDocumentsForDevice(UUID id) {
        return documentRepository.findAllByDeviceId(id)
                .stream()
                .map(documentMapper::mapDocumentEntityToDto)
                .toList();
    }

    public Document getDownloadDocumentData(UUID id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteDocument(UUID id) {
        documentRepository.deleteById(id);
    }

}
