package com.portfolio.service;

import com.portfolio.dto.JobRequest;
import com.portfolio.dto.JobResponse;

import java.util.List;

public interface IJobService {

    List<JobResponse> getAllJobs();
    JobResponse getJob(Long id);
    JobResponse createJob(JobRequest job);
    JobResponse updateJob(Long id, JobRequest job);
    void deleteJob(Long id);

}
