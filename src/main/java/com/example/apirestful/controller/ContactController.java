package com.example.apirestful.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import javax.validation.Valid;
import com.example.apirestful.model.Contact;
import com.example.apirestful.service.ContactService;

@Api(value = "APIRest Contact")
@RequestMapping("/contacts")
@RestController
public class ContactController {

    ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @ApiOperation(value = "Lista de Contatos/JSON")
    @GetMapping
    public List<Contact> getAll() {
        return contactService.findAll();
    }

    @ApiOperation(value = "Cadastrar Novo Contato/Envio em formato JSON")
    @PostMapping("/save")
    public void save(@Valid @RequestBody Contact contact) {
        contactService.save(contact);
    }

    @ApiOperation(value = "Atualizar Contato/Envio em formato JSON")
    @PutMapping("/save")
    public Contact update(@Valid @RequestBody Contact contact) {
        return contactService.update(contact);
    }

    @ApiOperation(value = "Buscar Contato(s) Por Telefone. Digite os primeiros Digitos ou Completo")
    @GetMapping("/{number}")
    public List<Contact> searchByNumber(@PathVariable String number) {
        return contactService.searchContactsByNumber(number);
    }

    @ApiOperation(value = "Excluir Contato por Telefone/Digite O Número Completo")
    @DeleteMapping("/{number}")
    public void delete(@PathVariable String number) {
        contactService.deleteContactByNumber(number);
    }
}