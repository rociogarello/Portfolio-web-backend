package com.portfolio.controller;

import com.portfolio.dto.TechnologyRequest;
import com.portfolio.dto.TechnologyResponse;
import com.portfolio.paths.PathName;
import com.portfolio.service.impl.TechnologyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(PathName.TECHNOLOGY)
public class TechnologyController {

    private final TechnologyServiceImpl technologyService;

    @GetMapping
    public ResponseEntity<List<TechnologyResponse>> getAllTechnologies() {
        return ResponseEntity.ok(technologyService.getAllTechnologies());
    }

    @GetMapping(PathName.PATH_ID)
    public ResponseEntity<TechnologyResponse> getTechnology(@PathVariable Long id) {
        return ResponseEntity.ok(technologyService.getTechnology(id));
    }

    @PostMapping
    public ResponseEntity<TechnologyResponse> createTechnology(
            @RequestParam MultipartFile file,
            @RequestParam String technologyName,
            @RequestParam Integer technologyLevel,
            @RequestParam Long personaId
    ) {
        TechnologyRequest technology = TechnologyRequest.builder()
                .technologyLevel(technologyLevel)
                .technologyName(technologyName)
                .personaId(personaId)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(technologyService.createTechnology(file, technology));
    }

    @PutMapping(PathName.PATH_ID)
    public ResponseEntity<TechnologyResponse> updateTechnology(
            @PathVariable Long id,
            @RequestParam MultipartFile file,
            @RequestParam String technologyName,
            @RequestParam Integer technologyLevel,
            @RequestParam Long personaId
    ) {
        TechnologyRequest technology = TechnologyRequest.builder()
                .technologyLevel(technologyLevel)
                .technologyName(technologyName)
                .personaId(personaId)
                .build();
        return ResponseEntity.ok(technologyService.updateTechnology(id, file, technology));
    }

    @DeleteMapping(PathName.PATH_ID)
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long id) {
        technologyService.deleteTechnology(id);
        return ResponseEntity.ok().build();
    }


}
