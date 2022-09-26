package com.portfolio.mapper;

import com.portfolio.dto.TechnologyRequest;
import com.portfolio.dto.TechnologyResponse;
import com.portfolio.entity.Persona;
import com.portfolio.entity.Technology;
import com.portfolio.repository.PersonaRepository;
import com.portfolio.service.IFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.portfolio.util.MapperUtil.streamNullableList;

@AllArgsConstructor
@Component
public class TechnologyMapperImpl implements IMapper<Technology, TechnologyRequest, TechnologyResponse> {

    private final PersonaRepository personaRepository;
    private final IFileService fileService;

    @Override
    public Technology toEntity(TechnologyRequest request) {
        Technology technology = Technology.builder()
                .technologyName(request.getTechnologyName())
                .technologyLevel(request.getTechnologyLevel())
                .image(request.getImage())
                .build();

        if (request.getPersonaId() != null && personaRepository.existsById(request.getPersonaId())) {
            Persona persona = personaRepository.getReferenceById(request.getPersonaId());
            persona.addTechnology(technology);
        }

        return technology;
    }

    @Override
    public Technology toEntity(Technology entity, TechnologyRequest request) {
        entity.setTechnologyName(request.getTechnologyName());
        entity.setTechnologyLevel(request.getTechnologyLevel());
        entity.setImage(request.getImage());

        if (request.getPersonaId() != null && personaRepository.existsById(request.getPersonaId())) {
            Persona persona = personaRepository.getReferenceById(request.getPersonaId());
            persona.addTechnology(entity);
        }

        return entity;
    }

    @Override
    public TechnologyResponse toDto(Technology entity) {
        TechnologyResponse response = TechnologyResponse.builder()
                .id(entity.getId())
                .technologyLevel(entity.getTechnologyLevel())
                .technologyName(entity.getTechnologyName())
                .build();

        if (entity.getImage() != null) {
            response.setImage(fileService.downloadFile(entity.getImage()));
        }

        return response;
    }

    @Override
    public List<TechnologyResponse> toDtoList(List<Technology> list) {
        return streamNullableList(list, this::toDto);
    }
}
