package com.example.apirestful;

import com.example.apirestful.model.Contact;
import com.example.apirestful.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	ContactService contactService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		contactService.save(new Contact("Pai de Familia", "(11) 4002-8922"));
		contactService.save(new Contact("Cascão", "(23) 4002-2343"));
		contactService.save(new Contact("Greg", "(22) 4354-3535"));
		contactService.save(new Contact("Logan", "(82) 9823-2343"));
		contactService.save(new Contact("Mr. Frog", "(42) 5685-5646"));
	}
}
