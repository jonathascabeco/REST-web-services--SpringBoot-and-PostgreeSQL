package com.jonathascabeco.stswebservicesproject.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@DeleteMapping(value = "/{id}")
	//nao pode deletar usuario com pedido, seria uma violação de integridade;
	//para deletar no padrao Rest o metodo http para deletar é o "delete"
	public ResponseEntity<Void> delete(@PathVariable Long id){
		//nao vai retornar nada no corpo somente deletar por isso void
		// pra o long id posssa ser reconhecido como uma variavel  da url coloca-se a anotação acima;
		service.delete(id);
		return ResponseEntity.noContent().build();
		//nocontent retorna uma resposta vazia, codigo http 204, ele ja trata isso;
	}
	
	@PutMapping(value = "/{id}")
	//no padrao rest para dar update utiliza-se put
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj ){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
