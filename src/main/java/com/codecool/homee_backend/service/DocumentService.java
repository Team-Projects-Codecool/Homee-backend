package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.document.DocumentDto;
import com.codecool.homee_backend.controller.dto.document.NewDocumentDto;
import com.codecool.homee_backend.entity.Device;
import com.codecool.homee_backend.entity.Document;
import com.codecool.homee_backend.mapper.DocumentMapper;
import com.codecool.homee_backend.repository.DeviceRepository;
import com.codecool.homee_backend.repository.DocumentRepository;
import com.codecool.homee_backend.service.exception.DeviceNotFoundException;
import com.codecool.homee_backend.service.exception.DocumentNotFoundException;
import com.codecool.homee_backend.utils.UploadsManagerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DeviceRepository deviceRepository;
    private final DocumentMapper documentMapper;

    public DocumentService(DocumentRepository documentRepository, DeviceRepository deviceRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.deviceRepository = deviceRepository;
        this.documentMapper = documentMapper;
    }

    public void addNewDocument(NewDocumentDto newDocument, String filePath) {
        Device device = deviceRepository.findById(newDocument.deviceId())
                .orElseThrow(() -> new DeviceNotFoundException(newDocument.deviceId()));
        Document document = documentMapper.mapDocumentDtoToEntity(newDocument);
        document.setDevice(device);
        document.setPath(filePath);
        documentRepository.save(document);
    }

    public DocumentDto getDocument(UUID id) {
        return documentRepository.findById(id)
                .map(documentMapper::mapDocumentEntityToDto)
                .orElseThrow(() -> new DocumentNotFoundException(id));
    }

    public List<DocumentDto> getDocumentsForDevice(UUID id) {
        return documentRepository.findAllByDeviceId(id)
                .stream()
                .map(documentMapper::mapDocumentEntityToDto)
                .toList();
    }

    public Document getDownloadDocumentData(UUID id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));
    }

    public void deleteDocument(UUID id) throws IOException {
        Document document = documentRepository.findById(id)
                        .orElseThrow(() -> new DocumentNotFoundException(id));
        UploadsManagerUtil.deleteDocumentFile(document);
        documentRepository.deleteById(id);
    }

    public HttpStatus saveDocument(String deviceId, String documentName, MultipartFile file) {
        try {
            String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String fileName = UUID.randomUUID() + originalFileName;
            UploadsManagerUtil.saveDocument(fileName, file);
            NewDocumentDto dto = new NewDocumentDto(UUID.fromString(deviceId), documentName);
            addNewDocument(dto, fileName);
            return HttpStatus.OK;
        } catch (Exception e) {
            log.warn(e.getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
