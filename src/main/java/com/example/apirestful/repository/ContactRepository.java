package com.example.apirestful.repository;

import java.util.List;
import javax.transaction.Transactional;
import com.example.apirestful.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Transactional
    void deleteByNumber(String name);

    List<Contact> findByNumberContaining(String number);
}