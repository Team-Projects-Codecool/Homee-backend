package com.codecool.homee_backend.controller;


import com.codecool.homee_backend.controller.dto.document.DocumentDto;
import com.codecool.homee_backend.controller.dto.document.NewDocumentDto;
import com.codecool.homee_backend.entity.Document;
import com.codecool.homee_backend.service.DocumentService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
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
    public List<DocumentDto> getDocumentsDataForDevice(UUID id) {
        return documentService.getDocumentsForDevice(id);
    }

    @GetMapping(value = "/{id}", params = "download")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable UUID id, @RequestParam Boolean download) {
        if (download) {
            Document document = documentService.getDownloadDocumentData(id);
            try {
                Path path = Paths.get(document.getPath());
                byte[] fileContent = Files.readAllBytes(path);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDisposition(ContentDisposition.builder("attachment")
                        .filename(path.getFileName().toString())
                        .build());
                headers.setContentLength(fileContent.length);

                return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);

            } catch (IOException e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable UUID id) {
        documentService.deleteDocument(id);
    }

    @PostMapping
    public ResponseEntity<Document> uploadDocument(@RequestBody NewDocumentDto newDocument, @RequestParam("file") MultipartFile file) {
       try {
           String filePath = saveFile(file, newDocument);
           Document document = documentService.addNewDocument(newDocument, filePath);
           return new ResponseEntity<>(document, HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    private String saveFile(MultipartFile file, NewDocumentDto newDocument) throws IOException {
        String fileName = UUID.randomUUID() + ".pdf";
        System.getProperty("java.home");
        //String.join("/", "uploads", "directory");
        String filePath = "/uploads/" + newDocument.deviceId() + "/" + fileName;
        File directory = new File("/uploads/" + newDocument.deviceId());
        if (!directory.exists()) {
            directory.mkdirs();
        }
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());
        return filePath;
    }
}
