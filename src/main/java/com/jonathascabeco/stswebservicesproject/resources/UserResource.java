package com.jonathascabeco.stswebservicesproject.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonathascabeco.stswebservicesproject.entities.User;





@RestController // recurso web que é implementado por um contolador Rest se identifica dessa forma;
@RequestMapping(value = "/users")
public class UserResource {
	//essa classe disponibiliza recurso web correspondente a entidade User;
	
	
	
	//método de resposta do sprinboot
	//controlador rest que responde no caminho users;
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "Maria", "maria@gmail.com", "99999999", "12345");
		return ResponseEntity.ok().body(u);
	}
	
}
