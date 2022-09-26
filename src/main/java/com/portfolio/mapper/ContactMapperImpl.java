package com.portfolio.mapper;

import com.portfolio.dto.ContactRequest;
import com.portfolio.dto.ContactResponse;
import com.portfolio.entity.Contact;
import com.portfolio.entity.Persona;
import com.portfolio.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.portfolio.util.MapperUtil.streamNullableList;

@AllArgsConstructor
@Component
public class ContactMapperImpl implements IMapper<Contact, ContactRequest, ContactResponse> {

    private final PersonaRepository personaRepository;

    @Override
    public Contact toEntity(ContactRequest request) {
        Contact contact = Contact.builder()
                .description(request.getDescription())
                .contact(request.getContact())
                .build();

        if (request.getPersonaId() != null && personaRepository.existsById(request.getPersonaId())) {
            Persona persona = personaRepository.getReferenceById(request.getPersonaId());
            persona.addContact(contact);
        }

        return contact;
    }

    @Override
    public Contact toEntity(Contact entity, ContactRequest request) {
        entity.setContact(request.getContact());
        entity.setDescription(request.getDescription());

        if (request.getPersonaId() != null && personaRepository.existsById(request.getPersonaId())) {
            Persona persona = personaRepository.getReferenceById(request.getPersonaId());
            persona.addContact(entity);
        }
        return entity;
    }

    @Override
    public ContactResponse toDto(Contact entity) {
        return ContactResponse.builder()
                .id(entity.getId())
                .contact(entity.getContact())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public List<ContactResponse> toDtoList(List<Contact> list) {
        return streamNullableList(list, this::toDto);
    }

}
