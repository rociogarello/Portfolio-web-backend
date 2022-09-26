package com.portfolio.service;

import com.portfolio.dto.EducationRequest;
import com.portfolio.dto.EducationResponse;

import java.util.List;

public interface IEducationService {

    List<EducationResponse> getAllEducation();
    EducationResponse getEducation(Long id);
    EducationResponse createEducation(EducationRequest educationRequest);
    EducationResponse updateEducation(Long id, EducationRequest educationRequest);
    void deleteEducation(Long id);

}
