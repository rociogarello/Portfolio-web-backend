package com.portfolio.controller;

import com.portfolio.dto.ProjectRequest;
import com.portfolio.dto.ProjectResponse;
import com.portfolio.paths.PathName;
import com.portfolio.service.impl.ProjectServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(PathName.PROJECT)
public class ProjectController {

    private final ProjectServiceImpl projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @RequestParam MultipartFile file,
            @RequestParam String projectName,
            @RequestParam String description,
            @RequestParam String site,
            @RequestParam Long personaId,
            @RequestParam List<Long> technologiesId
    ) {
        ProjectRequest projectRequest = ProjectRequest.builder()
                .projectName(projectName)
                .description(description)
                .personaId(personaId)
                .site(site)
                .technologiesId(technologiesId)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(file, projectRequest));
    }

    @GetMapping(PathName.PATH_ID)
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @PutMapping(PathName.PATH_ID)
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long id,
            @RequestParam MultipartFile file,
            @RequestParam String projectName,
            @RequestParam String description,
            @RequestParam String site,
            @RequestParam Long personaId,
            @RequestParam List<Long> technologiesId
    ) {
        ProjectRequest projectRequest = ProjectRequest.builder()
                .projectName(projectName)
                .description(description)
                .personaId(personaId)
                .site(site)
                .technologiesId(technologiesId)
                .build();
        return ResponseEntity.ok(projectService.updateProject(id, file, projectRequest));
    }

    @DeleteMapping(PathName.PATH_ID)
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

}
