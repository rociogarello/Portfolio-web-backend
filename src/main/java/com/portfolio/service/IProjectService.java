package com.portfolio.service;

import com.portfolio.dto.ProjectRequest;
import com.portfolio.dto.ProjectResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProjectService {

    List<ProjectResponse> getAllProjects();
    ProjectResponse getProject(Long id);
    ProjectResponse createProject(MultipartFile file, ProjectRequest projectRequest);
    ProjectResponse updateProject(Long id, MultipartFile file, ProjectRequest projectRequest);
    void deleteProject(Long id);

}
