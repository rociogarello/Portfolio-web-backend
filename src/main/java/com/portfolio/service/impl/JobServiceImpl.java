package com.portfolio.service.impl;

import com.portfolio.dto.JobRequest;
import com.portfolio.dto.JobResponse;
import com.portfolio.entity.Job;
import com.portfolio.mapper.JobMapperImpl;
import com.portfolio.repository.JobRepository;
import com.portfolio.service.IJobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class JobServiceImpl implements IJobService {

    private final JobRepository jobRepository;
    private final JobMapperImpl jobMapper;

    @Override
    public List<JobResponse> getAllJobs() {
        return jobMapper.toDtoList(jobRepository.findAll());
    }

    @Override
    public JobResponse getJob(Long id) {
        return jobMapper.toDto(jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found.")));
    }

    @Override
    public JobResponse createJob(JobRequest job) {
        return jobMapper.toDto(jobRepository.save(jobMapper.toEntity(job)));
    }

    @Override
    public JobResponse updateJob(Long id, JobRequest job) {
        Job jobEntity = jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found."));
        return jobMapper.toDto(jobRepository.save(jobMapper.toEntity(jobEntity, job)));
    }

    @Override
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new RuntimeException("Job not found.");
        }
        jobRepository.deleteById(id);
    }

}
