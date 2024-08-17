package br.ufscar.dc.dsw.SistemaVagas.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    private final Path rootLocation;

    public StorageService(@Value("${storage.location}") String storageLocation) {
        this.rootLocation = Paths.get(storageLocation);
        initialize();
    }

    public void initialize() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    public String store(MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            Path destinationFile = this.rootLocation.resolve(Paths.get(filename))
                    .normalize();
            if (!destinationFile.getParent().equals(this.rootLocation)) {
                throw new RuntimeException("Cannot store file outside current directory.");
            }
            try (var inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile);
            }
            return destinationFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    public void deleteFile(String filename) {
        Path file = rootLocation.resolve(filename);
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file: " + filename, e);
        }
    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }
}
