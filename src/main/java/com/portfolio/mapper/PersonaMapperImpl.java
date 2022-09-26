package com.portfolio.mapper;

import com.portfolio.dto.*;
import com.portfolio.entity.*;
import com.portfolio.repository.*;
import com.portfolio.service.IFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.portfolio.util.MapperUtil.streamNullableList;

@Slf4j
@AllArgsConstructor
@Component
public class PersonaMapperImpl implements IMapper<Persona, PersonaRequest, PersonaResponse> {

    private final JobRepository jobRepository;
    private final EducationRepository educationRepository;
    private final AboutRepository aboutRepository;
    private final ContactRepository contactRepository;
    private final ProjectRepository projectRepository;
    private final TechnologyRepository technologyRepository;
    private final IFileService fileService;

    @Override
    public Persona toEntity(PersonaRequest request) {
        return Persona.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

    }

    @Override
    public Persona toEntity(Persona entity, PersonaRequest request) {
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());

        if (request.getAboutId() != null && aboutRepository.existsById(request.getAboutId())) {
            entity.addAbout(aboutRepository.getReferenceById(request.getAboutId()));
        }

        if (!CollectionUtils.isEmpty(request.getContactListIds())) {
            contactRepository.findAllById(request.getContactListIds()).forEach(entity::addContact);
        }

        if (!CollectionUtils.isEmpty(request.getEducationListIds())) {
            educationRepository.findAllById(request.getEducationListIds()).forEach(entity::addEducation);
        }

        if (!CollectionUtils.isEmpty(request.getJobListIds())) {
            jobRepository.findAllById(request.getJobListIds()).forEach(entity::addJob);
        }

        if (!CollectionUtils.isEmpty(request.getProjectListIds())) {
            projectRepository.findAllById(request.getProjectListIds()).forEach(entity::addProject);
        }

        if (!CollectionUtils.isEmpty(request.getTechnologyListIds())) {
            technologyRepository.findAllById(request.getTechnologyListIds()).forEach(entity::addTechnology);
        }

        return entity;
    }

    @Override
    public PersonaResponse toDto(Persona entity) {
        PersonaResponse personaResponse = PersonaResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();

        if (entity.getAbout() != null) {
            About about = entity.getAbout();
            AboutResponse aboutResponse = AboutResponse.builder()
                    .id(about.getId())
                    .about(about.getAbout())
                    .title(about.getTitle())
                    .build();

            if (about.getImage() != null) {
                aboutResponse.setImage(fileService.downloadFile(about.getImage()));
            }
            personaResponse.setAbout(aboutResponse);
        }

        if (entity.getProjectList() != null && !entity.getProjectList().isEmpty()) {
            List<Project> projectList = entity.getProjectList();
            List<ProjectResponse> projectResponseList = projectList.stream().map(project -> {
                ProjectResponse projectResponse = ProjectResponse.builder()
                        .id(project.getId())
                        .site(project.getSite())
                        .projectName(project.getProjectName())
                        .description(project.getDescription())
                        .build();

                if (project.getImage() != null) {
                    projectResponse.setImage(fileService.downloadFile(project.getImage()));
                }

                if (project.getTechnologyList() != null && !project.getTechnologyList().isEmpty()) {
                    List<TechnologyResponse> technologyResponseList = project.getTechnologyList().stream().map(technology -> {
                        TechnologyResponse technologyResponse = TechnologyResponse.builder()
                                .id(technology.getId())
                                .technologyLevel(technology.getTechnologyLevel())
                                .technologyName(technology.getTechnologyName())
                                .build();

                        if (technology.getImage() != null) {
                            technologyResponse.setImage(fileService.downloadFile(technology.getImage()));
                        }
                        return technologyResponse;
                    }).collect(Collectors.toList());

                    projectResponse.setTechnologyList(technologyResponseList);
                }

                return projectResponse;
            }).collect(Collectors.toList());

            personaResponse.setProjectList(projectResponseList);
        }

        if (entity.getContactList() != null && !entity.getContactList().isEmpty()) {
            List<Contact> contactList = entity.getContactList();
            List<ContactResponse> contactResponseList = contactList.stream().map(contact -> ContactResponse.builder()
                    .id(contact.getId())
                    .contact(contact.getContact())
                    .description(contact.getDescription())
                    .build())
                    .collect(Collectors.toList());

            personaResponse.setContactList(contactResponseList);
        }

        if (entity.getEducationList() != null && !entity.getEducationList().isEmpty()) {
            List<Education> educationList = entity.getEducationList();
            List<EducationResponse> educationResponseList = educationList.stream().map(education -> EducationResponse.builder()
                    .id(education.getId())
                    .title(education.getTitle())
                    .institute(education.getInstitute())
                    .startDate(education.getStartDate())
                    .endDate(education.getEndDate())
                    .build())
                    .collect(Collectors.toList());

            personaResponse.setEducationList(educationResponseList);
        }

        if (entity.getTechnologyList() != null && !entity.getTechnologyList().isEmpty()) {
            List<Technology> technologyList = entity.getTechnologyList();
            List<TechnologyResponse> technologyResponseList = technologyList.stream().map(technology -> {
                TechnologyResponse technologyResponse = TechnologyResponse.builder()
                        .id(technology.getId())
                        .technologyName(technology.getTechnologyName())
                        .technologyLevel(technology.getTechnologyLevel())
                        .build();

                return technologyResponse;
            }).collect(Collectors.toList());

            personaResponse.setTechnologyList(technologyResponseList);
        }

        if (entity.getJobList() != null && !entity.getJobList().isEmpty()) {
            List<Job> jobList = entity.getJobList();
            List<JobResponse> jobResponseList = jobList.stream().map(job -> JobResponse.builder()
                            .id(job.getId())
                            .jobRole(job.getJobRole())
                            .jobDescription(job.getJobDescription())
                            .startDate(job.getStartDate())
                            .endDate(job.getEndDate())
                            .companyName(job.getCompanyName())
                            .build())
                    .collect(Collectors.toList());

            personaResponse.setJobList(jobResponseList);
        }
        return personaResponse;
    }

    @Override
    public List<PersonaResponse> toDtoList(List<Persona> list) {
        return streamNullableList(list, this::toDto);
    }
}
