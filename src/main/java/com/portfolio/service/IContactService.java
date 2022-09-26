package com.portfolio.service;

import com.portfolio.dto.ContactRequest;
import com.portfolio.dto.ContactResponse;

import java.util.List;

public interface IContactService {

        List<ContactResponse> getAllContacts();

        ContactResponse getContact(Long id);

        ContactResponse saveContact(ContactRequest contact);

        ContactResponse updateContact(Long id, ContactRequest contact);

        void deleteContact(Long id);


}
