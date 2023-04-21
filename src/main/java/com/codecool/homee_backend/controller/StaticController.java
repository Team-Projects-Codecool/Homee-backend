package com.codecool.homee_backend.controller;

import org.apache.tika.Tika;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/static")
public class StaticController {

    @GetMapping("/user-photos/{fileName:.+}")
    public ResponseEntity<byte[]> serveUserPhotos(@PathVariable String fileName) throws IOException {
        Path staticDir = Paths.get("src", "main", "static", "user-photos");
        Path filePath = staticDir.resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists() && resource.isReadable()) {
            byte[] fileContent = resource.getInputStream().readAllBytes();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(new Tika().detect(filePath)));
            return new ResponseEntity<>(fileContent, headers, 200);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/device-photos/{fileName:.+}")
    public ResponseEntity<byte[]> serveDevicePhotos(@PathVariable String fileName) throws IOException {
        Path staticDir = Paths.get("src", "main", "static", "device-photos");
        Path filePath = staticDir.resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists() && resource.isReadable()) {
            byte[] fileContent = resource.getInputStream().readAllBytes();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(new Tika().detect(filePath)));
            return new ResponseEntity<>(fileContent, headers, 200);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
