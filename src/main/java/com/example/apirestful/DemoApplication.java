package com.example.apirestful;

import java.util.List;
import javax.validation.Valid;
import com.example.apirestful.model.Contact;
import com.example.apirestful.repository.ContactRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoApplication {

	ContactRepository contactRepository;

	public DemoApplication(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
		contactRepository.save(new Contact("Junior", "(11) 4002-8922"));
		contactRepository.save(new Contact("Anderson", "(82) 99966-8440"));
	}

	@GetMapping
	public List<Contact> getAll() {
		return contactRepository.findAll();
	}

	@PostMapping("/save")
	public Contact save(@Valid @RequestBody Contact contact) {
		return contactRepository.save(contact);
	}

	@PutMapping("/save")
	public Contact update(@Valid @RequestBody Contact contact) {
		return contactRepository.save(contact);
	}

	@GetMapping("/{number}")
	public List<Contact> searchByNumber(@PathVariable String number) {
		return contactRepository.findByNumberContaining(number);
	}

	@DeleteMapping("/{number}")
	public void delete(@PathVariable String number) {
		contactRepository.deleteByNumber(number);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
