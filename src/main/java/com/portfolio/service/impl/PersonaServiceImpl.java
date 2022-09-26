package com.portfolio.service.impl;

import com.portfolio.dto.PersonaRequest;
import com.portfolio.dto.PersonaResponse;
import com.portfolio.entity.Persona;
import com.portfolio.mapper.PersonaMapperImpl;
import com.portfolio.repository.PersonaRepository;
import com.portfolio.service.IPersonaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class PersonaServiceImpl implements IPersonaService {

    private final PersonaRepository personaRepository;
    private final PersonaMapperImpl personaMapper;

    public PersonaResponse getPersona() {
        return personaMapper.toDto(personaRepository.findFirstByOrderById()
                .orElseThrow(() -> new EntityNotFoundException("Persona not found.")));
    }

    public PersonaResponse createPersona(PersonaRequest entity) {
        if (personaRepository.findFirstByOrderById().isPresent()) {
            throw new RuntimeException("Persona already exists.");
        }
        return personaMapper.toDto(personaRepository.save(personaMapper.toEntity(entity)));
    }

    public PersonaResponse updatePersona(Long id, PersonaRequest entity) {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new RuntimeException("Persona not found."));
        return personaMapper.toDto(personaRepository.save(personaMapper.toEntity(persona, entity)));
    }

}
