package com.portfolio.mapper;

import com.portfolio.dto.AboutRequest;
import com.portfolio.dto.AboutResponse;
import com.portfolio.entity.About;
import com.portfolio.entity.Persona;
import com.portfolio.repository.PersonaRepository;
import com.portfolio.service.IFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class AboutMapperImpl implements IMapper<About, AboutRequest, AboutResponse> {

    private final PersonaRepository personaRepository;
    private final IFileService fileService;

    @Override
    public About toEntity(AboutRequest request) {
        About about = About.builder()
                .title(request.getTitle())
                .about(request.getAbout())
                .build();

        if (request.getPersonaId() != null && personaRepository.existsById(request.getPersonaId())) {
             Persona persona = personaRepository.getReferenceById(request.getPersonaId());
             persona.addAbout(about);
        }

        return about;
    }

    @Override
    public About toEntity(About entity, AboutRequest request) {
        if (request.getPersonaId() != null && personaRepository.existsById(request.getPersonaId())) {
            Persona persona = personaRepository.getReferenceById(request.getPersonaId());
            persona.addAbout(entity);
        }
        entity.setTitle(request.getTitle());
        entity.setAbout(request.getAbout());
        return entity;
    }

    @Override
    public AboutResponse toDto(About entity) {
        AboutResponse aboutResponse = AboutResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .about(entity.getAbout())
                .build();

        if (entity.getImage() != null) {
            aboutResponse.setImage(fileService.downloadFile(entity.getImage()));
        }
        return aboutResponse;
    }

    @Override
    public List<AboutResponse> toDtoList(List<About> list) {
        return null;
    }




}
