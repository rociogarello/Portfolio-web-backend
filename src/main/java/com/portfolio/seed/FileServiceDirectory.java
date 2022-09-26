package com.portfolio.seed;

import com.portfolio.service.IFileService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FileServiceDirectory implements CommandLineRunner {

    private final IFileService fileService;

    @Override
    public void run(String... args) throws Exception {
        fileService.createDirectory();
    }
}
