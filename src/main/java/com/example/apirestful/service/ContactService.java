package com.example.apirestful.service;

import java.util.List;

import com.example.apirestful.model.Contact;

public interface ContactService {

    public void save(Contact contact);
    public Contact update(Contact contact);
    public List<Contact> findAll();
    public List<Contact> searchContactsByNumber(String number);
    public void deleteContactByNumber(String number);
    
}