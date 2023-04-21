package com.codecool.homee_backend.utils;

import com.codecool.homee_backend.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadsManagerUtil {

    public static void saveUserPhoto(String fileName,
                                MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get("src", "main", "static", "user-photos");
        saveFile(fileName, multipartFile, uploadPath);
    }

    public static void saveDevicePhoto(String fileName,
                                     MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get("src", "main", "static", "device-photos");
        saveFile(fileName, multipartFile, uploadPath);
    }

    public static void deleteDocumentFile(Document document) throws IOException {
        Path filePath = Paths.get("src", "main", "user-uploads", document.getPath());
        deleteFile(filePath);
    }


    public static void saveDocument(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get("src", "main", "user-uploads");
        saveFile(fileName, multipartFile, uploadPath);
    }

    private static void saveFile(String fileName, MultipartFile multipartFile, Path uploadPath) throws IOException {
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }
    }

    private static void deleteFile(Path filePath) throws IOException {
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        } else {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }
}