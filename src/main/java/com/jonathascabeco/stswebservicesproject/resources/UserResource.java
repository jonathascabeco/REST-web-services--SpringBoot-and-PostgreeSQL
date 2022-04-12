package com.jonathascabeco.stswebservicesproject.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jonathascabeco.stswebservicesproject.entities.User;
import com.jonathascabeco.stswebservicesproject.services.UserService;

@RestController // recurso web que é implementado por um contolador Rest se identifica dessa forma;
@RequestMapping(value = "/users")
public class UserResource {
	//essa classe disponibiliza recurso web correspondente a entidade User;
	
	@Autowired
	private UserService service;
	//para isso funcionar a classe userservice tem de estar registrada como componente do 
	//spring
	
	//método de resposta do sprinboot
	//controlador rest que responde no caminho users;
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//endpoint para bscar usuario por id
	@GetMapping (value = "/{id}") 
	//requisição por id; metodo para declarar que a url tera um parametro id;
	public ResponseEntity<User> findById(@PathVariable Long id){
		//para considera-lo como parametro que chegara na url eh necessario essa anotação no campo de parametros;
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){ //transformar json em obj User;
		obj = service.insert(obj);
		//converte para uri, metodo de resposta do http;
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);		
	}
	
}
