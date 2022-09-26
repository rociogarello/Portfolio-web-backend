package com.portfolio.service;

import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

public interface IFileService {

    void createDirectory();
    URI saveFile(MultipartFile file);
    byte[] downloadFile(String filename);
    void deleteFile(String filename);

}
