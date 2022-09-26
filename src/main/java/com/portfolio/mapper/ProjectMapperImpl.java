package com.portfolio.mapper;

import com.portfolio.dto.ProjectRequest;
import com.portfolio.dto.ProjectResponse;
import com.portfolio.dto.TechnologyResponse;
import com.portfolio.entity.Persona;
import com.portfolio.entity.Project;
import com.portfolio.entity.Technology;
import com.portfolio.repository.PersonaRepository;
import com.portfolio.repository.TechnologyRepository;
import com.portfolio.service.IFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.portfolio.util.MapperUtil.*;

@Component
@AllArgsConstructor
public class ProjectMapperImpl implements IMapper<Project, ProjectRequest, ProjectResponse> {

    private final PersonaRepository personaRepository;
    private final TechnologyRepository technologyRepository;
    private final IFileService fileService;

    @Override
    public Project toEntity(ProjectRequest request) {
        Project project = Project.builder()
                .projectName(request.getProjectName())
                .description(request.getDescription())
                .site(request.getSite())
                .image(request.getImage())
                .build();
        if (request.getTechnologiesId() != null && !request.getTechnologiesId().isEmpty()) {
            List<Technology> technologies = technologyRepository.findAllById(request.getTechnologiesId());
            project.setTechnologyList(technologies);
        }

        if (request.getPersonaId() != null && personaRepository.existsById(request.getPersonaId())) {
            Persona persona = personaRepository.getReferenceById(request.getPersonaId());
            persona.addProject(project);
        }

        return project;
    }

    @Override
    public Project toEntity(Project entity, ProjectRequest request) {
        entity.setProjectName(request.getProjectName());
        entity.setDescription(request.getDescription());
        entity.setSite(request.getSite());

        if (request.getTechnologiesId() != null && !request.getTechnologiesId().isEmpty()) {
            List<Technology> technologies = technologyRepository.findAllById(request.getTechnologiesId());
            entity.setTechnologyList(technologies);
        }

        if (request.getPersonaId() != null && personaRepository.existsById(request.getPersonaId())) {
            Persona persona = personaRepository.getReferenceById(request.getPersonaId());
            persona.addProject(entity);
        }

        return entity;
    }

    @Override
    public ProjectResponse toDto(Project entity) {
        ProjectResponse projectResponse = ProjectResponse.builder()
                .id(entity.getId())
                .projectName(entity.getProjectName())
                .description(entity.getDescription())
                .site(entity.getSite())
                .build();
        
        if (entity.getImage() != null) {
            projectResponse.setImage(fileService.downloadFile(entity.getImage()));
        }

        if (entity.getTechnologyList() != null && !entity.getTechnologyList().isEmpty()) {
            List<Technology> technologyList = entity.getTechnologyList();
            List<TechnologyResponse> technologyResponseList = technologyList.stream().map(technology -> {
                                TechnologyResponse response = TechnologyResponse.builder()
                                        .id(technology.getId())
                                        .technologyLevel(technology.getTechnologyLevel())
                                        .technologyName(technology.getTechnologyName())
                                        .build();

                                if (technology.getImage() != null) {
                                    response.setImage(fileService.downloadFile(technology.getImage()));
                                }

                                return response;
                            }
                    )
                    .collect(Collectors.toList());

            projectResponse.setTechnologyList(technologyResponseList);
        }
        return projectResponse;
    }

    @Override
    public List<ProjectResponse> toDtoList(List<Project> list) {
        return streamNullableList(list, this::toDto);
    }
}
