package com.portfolio.service.impl;

import com.portfolio.dto.ProjectRequest;
import com.portfolio.dto.ProjectResponse;
import com.portfolio.entity.Project;
import com.portfolio.mapper.ProjectMapperImpl;
import com.portfolio.repository.ProjectRepository;
import com.portfolio.service.IFileService;
import com.portfolio.service.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements IProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapperImpl projectMapper;
    private final IFileService fileService;

    @Override
    public List<ProjectResponse> getAllProjects() {
        return projectMapper.toDtoList(projectRepository.findAll());
    }

    @Override
    public ProjectResponse getProject(Long id) {
        return projectMapper.toDto(projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found.")));
    }

    @Override
    public ProjectResponse createProject(MultipartFile file, ProjectRequest projectRequest) {
        projectRequest.setImage(fileService.saveFile(file).toString());
        return projectMapper.toDto(projectRepository.save(projectMapper.toEntity(projectRequest)));
    }

    @Override
    public ProjectResponse updateProject(Long id, MultipartFile file, ProjectRequest projectRequest) {
        Project entity = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found."));
        if (entity.getImage() != null) {
            fileService.deleteFile(entity.getImage());
        }
        entity.setImage(fileService.saveFile(file).toString());
        return projectMapper.toDto(projectRepository.save(projectMapper.toEntity(entity, projectRequest)));
    }

    @Override
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) throw new RuntimeException("Project not found.");
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent() && project.get().getImage() != null) fileService.deleteFile(project.get().getImage());
        projectRepository.deleteById(id);
    }
}
