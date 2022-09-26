package com.portfolio.controller;

import com.portfolio.dto.AboutRequest;
import com.portfolio.dto.AboutResponse;
import com.portfolio.paths.PathName;
import com.portfolio.service.impl.AboutServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(PathName.ABOUT)
public class AboutController {

    private final AboutServiceImpl aboutService;

    @GetMapping
    public ResponseEntity<AboutResponse> getAbout() {
        return ResponseEntity.ok(aboutService.getAbout());
    }

    @PostMapping
    public ResponseEntity<AboutResponse> createAbout(@Valid @RequestBody AboutRequest aboutRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aboutService.createAbout(aboutRequest));
    }

    @PutMapping(PathName.PATH_ID)
    public ResponseEntity<AboutResponse> updateAbout(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            @RequestParam String title,
            @RequestParam String about,
            @RequestParam Long personaId
    ) {
        AboutRequest aboutRequest = AboutRequest.builder()
                .title(title)
                .about(about)
                .personaId(personaId)
                .build();

        return ResponseEntity.ok(aboutService.updateAbout(id, file, aboutRequest));
    }

}
