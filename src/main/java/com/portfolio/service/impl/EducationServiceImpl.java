package com.portfolio.service.impl;

import com.portfolio.dto.EducationRequest;
import com.portfolio.dto.EducationResponse;
import com.portfolio.entity.Education;
import com.portfolio.mapper.EducationMapperImpl;
import com.portfolio.repository.EducationRepository;
import com.portfolio.service.IEducationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EducationServiceImpl implements IEducationService {

    private final EducationRepository educationRepository;
    private final EducationMapperImpl educationMapper;

    @Override
    public List<EducationResponse> getAllEducation() {
        return educationMapper.toDtoList(educationRepository.findAll());
    }

    @Override
    public EducationResponse getEducation(Long id) {
        return educationMapper.toDto(educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found.")));
    }

    @Override
    public EducationResponse createEducation(EducationRequest educationRequest) {
        return educationMapper.toDto(educationRepository.save(educationMapper.toEntity(educationRequest)));
    }

    @Override
    public EducationResponse updateEducation(Long id, EducationRequest educationRequest) {
        Education entity = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found."));
        return educationMapper.toDto(educationRepository.save(educationMapper.toEntity(entity, educationRequest)));
    }

    @Override
    public void deleteEducation(Long id) {
        if (!educationRepository.existsById(id)) throw new RuntimeException("Education not found.");
        educationRepository.deleteById(id);
    }
}
