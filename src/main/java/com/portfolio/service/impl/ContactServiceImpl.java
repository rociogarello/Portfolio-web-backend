package com.portfolio.service.impl;

import com.portfolio.dto.ContactRequest;
import com.portfolio.dto.ContactResponse;
import com.portfolio.entity.Contact;
import com.portfolio.mapper.ContactMapperImpl;
import com.portfolio.repository.ContactRepository;
import com.portfolio.service.IContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;
    private final ContactMapperImpl contactMapper;

    @Override
    public List<ContactResponse> getAllContacts() {
        return contactMapper.toDtoList(contactRepository.findAll());
    }

    @Override
    public ContactResponse getContact(Long id) {
        return contactMapper.toDto(contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found")));
    }

    @Override
    public ContactResponse saveContact(ContactRequest contact) {
        return contactMapper.toDto(contactRepository.save(contactMapper.toEntity(contact)));
    }

    @Override
    public ContactResponse updateContact(Long id, ContactRequest contact) {
        Contact entity = contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found."));
        return contactMapper.toDto(contactRepository.save(contactMapper.toEntity(entity, contact)));
    }

    @Override
    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new RuntimeException("Contact not found.");
        }
        contactRepository.deleteById(id);
    }

}
