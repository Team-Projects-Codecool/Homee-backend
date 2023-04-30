package com.codecool.homee_backend.controller;


import com.codecool.homee_backend.controller.dto.document.DocumentDto;
import com.codecool.homee_backend.entity.Document;
import com.codecool.homee_backend.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static com.codecool.homee_backend.config.auth.SpringSecurityConfig.USER;

@RolesAllowed(USER)
@RestController
@Slf4j
@RequestMapping("/api/v1/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/{id}")
    public DocumentDto getDocumentData(@PathVariable UUID id) {
        return documentService.getDocument(id);
    }

    @GetMapping(params = "deviceId")
    public List<DocumentDto> getDocumentsDataForDevice(@RequestParam UUID deviceId) {
        return documentService.getDocumentsForDevice(deviceId);
    }

    @GetMapping(value = "/{id}", params = "download")
    public ResponseEntity<Resource> downloadDocument(@PathVariable UUID id, HttpServletRequest request) {
        Document document = documentService.getDownloadDocumentData(id);
        try {
            Path path = Paths.get("src", "main", "user-uploads", document.getPath());
            Resource resource = new UrlResource(path.toUri());
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                log.info("Could not determine file type.");
            }
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename(document.getPath())
                    .build());
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable UUID id) throws IOException {
        documentService.deleteDocument(id);
    }


    @PostMapping()
    public HttpStatus uploadDocument(@RequestParam String deviceId, @RequestParam String
            documentName, @RequestParam("file") MultipartFile file) {
        return documentService.saveDocument(deviceId, documentName, file);
    }

}
