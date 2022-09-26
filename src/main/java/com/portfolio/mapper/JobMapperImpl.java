package com.portfolio.mapper;

import com.portfolio.dto.JobRequest;
import com.portfolio.dto.JobResponse;
import com.portfolio.entity.Job;
import com.portfolio.entity.Persona;
import com.portfolio.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.portfolio.util.MapperUtil.streamNullableList;

@AllArgsConstructor
@Component
public class JobMapperImpl implements IMapper<Job, JobRequest, JobResponse> {

    private final PersonaRepository personaRepository;

    @Override
    public Job toEntity(JobRequest request) {
        Job job = Job.builder()
                .companyName(request.getCompanyName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .jobDescription(request.getJobDescription())
                .jobRole(request.getJobRole())
                .build();

        if (Boolean.TRUE.equals(request.getIsPresent())) {
            job.setEndDate(LocalDate.of(9999, 9, 9));
        }

        if (request.getPersonaId() != null && personaRepository.existsById(request.getPersonaId())) {
            Persona persona = personaRepository.getReferenceById(request.getPersonaId());
            persona.addJob(job);
        }

        return job;
    }

    @Override
    public Job toEntity(Job job, JobRequest request) {
        if (request.getPersonaId() != null && personaRepository.existsById(request.getPersonaId())) {
            Persona persona = personaRepository.getReferenceById(request.getPersonaId());
            persona.addJob(job);
        }
        job.setStartDate(request.getStartDate());
        job.setEndDate(request.getEndDate());
        job.setJobDescription(request.getJobDescription());
        job.setJobRole(request.getJobRole());
        job.setCompanyName(request.getCompanyName());
        return job;
    }

    @Override
    public JobResponse toDto(Job entity) {
        return JobResponse.builder()
                .id(entity.getId())
                .companyName(entity.getCompanyName())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .jobDescription(entity.getJobDescription())
                .jobRole(entity.getJobRole())
                .build();
    }

    @Override
    public List<JobResponse> toDtoList(List<Job> list) {
        return streamNullableList(list, this::toDto);
    }
}
