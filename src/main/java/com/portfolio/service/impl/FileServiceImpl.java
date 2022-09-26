package com.portfolio.service.impl;

import com.portfolio.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@Slf4j
@Service
public class FileServiceImpl implements IFileService {

    @Value("${custom.file.directory}")
    private String directory;

    @Override
    public void createDirectory() {
        Path path = Paths.get(directory);
        if (Files.isDirectory(path)) return;
        try {
            Files.createDirectory(path);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

    @Override
    public URI saveFile(MultipartFile multipartFile) {
        Path path = Path.of(directory);
        Path fileLocation = path.resolve(Instant.now().toEpochMilli() + multipartFile.getOriginalFilename());
        try {
            File file = new File(fileLocation.toString());
            OutputStream os = new FileOutputStream(file);
            os.write(multipartFile.getBytes());
            os.close();
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        return path.toUri().relativize(fileLocation.toUri());
    }

    @Override
    public byte[] downloadFile(String filename) {
        Path root = Paths.get(directory);
        Path filePath = root.resolve(filename);
        try {
            InputStream file = new FileInputStream(filePath.toString());
            byte[] bytes = file.readAllBytes();
            file.close();
            return bytes;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteFile(String filename) {
        try {
            Path root = Paths.get(directory);
            Path filePath = root.resolve(filename);
            Files.delete(filePath);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }
}
