package com.portfolio.service;

import com.portfolio.dto.PersonaRequest;
import com.portfolio.dto.PersonaResponse;

public interface IPersonaService {

    PersonaResponse getPersona();

    PersonaResponse createPersona(PersonaRequest entity);

    PersonaResponse updatePersona(Long id, PersonaRequest entity);

}

