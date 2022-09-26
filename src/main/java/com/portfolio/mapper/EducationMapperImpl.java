package com.portfolio.mapper;

import com.portfolio.dto.EducationRequest;
import com.portfolio.dto.EducationResponse;
import com.portfolio.entity.Education;
import com.portfolio.entity.Persona;
import com.portfolio.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.portfolio.util.MapperUtil.streamNullableList;

@AllArgsConstructor
@Component
public class EducationMapperImpl implements IMapper<Education, EducationRequest, EducationResponse> {

    private final PersonaRepository personaRepository;

    @Override
    public Education toEntity(EducationRequest request) {
        Education education = Education.builder()
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .institute(request.getInstitute())
                .title(request.getTitle())
                .build();

        if (Boolean.TRUE.equals(request.getIsPresent())) {
            education.setEndDate(LocalDate.of(9999, 9, 9));
        }

        if (request.getPersonaId() != null && personaRepository.existsById(request.getPersonaId())) {
            Persona persona = personaRepository.getReferenceById(request.getPersonaId());
            persona.addEducation(education);
        }

        return education;
    }

    @Override
    public Education toEntity(Education entity, EducationRequest request) {
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setInstitute(request.getInstitute());
        entity.setTitle(request.getTitle());

        if (request.getPersonaId() != null && personaRepository.existsById(request.getPersonaId())) {
            Persona persona = personaRepository.getReferenceById(request.getPersonaId());
            persona.addEducation(entity);
        }
        return entity;
    }

    @Override
    public EducationResponse toDto(Education entity) {
        return EducationResponse.builder()
                .id(entity.getId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .institute(entity.getInstitute())
                .title(entity.getTitle())
                .build();
    }

    @Override
    public List<EducationResponse> toDtoList(List<Education> list) {
        return streamNullableList(list, this::toDto);
    }
}
