package com.example.apirestful.service;

import java.util.List;
import javax.validation.Valid;
import com.example.apirestful.model.Contact;
import com.example.apirestful.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ContactServiceImplement implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact update(@RequestBody @Valid Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void save(@Valid @RequestBody Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public List<Contact> searchContactsByNumber(String number) {
        return contactRepository.findByNumberContaining(number);
    }

    @Override
    public void deleteContactByNumber(String number) {
        contactRepository.deleteByNumber(number);
    }
}