package com.jonathascabeco.stswebservicesproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonathascabeco.stswebservicesproject.entities.Category;
import com.jonathascabeco.stswebservicesproject.services.CategoryService;

@RestController // recurso web que é implementado por um contolador Rest se identifica dessa forma;
@RequestMapping(value = "/categories")
public class CategoryResource {
	//essa classe disponibiliza recurso web correspondente a entidade Category;
	
	@Autowired
	private CategoryService service;
	//para isso funcionar a classe userservice tem de estar registrada como componente do 
	//spring
	
	//método de resposta do sprinboot
	//controlador rest que responde no caminho users;
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//endpoint para bscar usuario por id
	@GetMapping (value = "/{id}") 
	//requisição por id; metodo para declarar que a url tera um parametro id;
	public ResponseEntity<Category> findById(@PathVariable Long id){
		//para considera-lo como parametro que chegara na url eh necessario essa anotação no campo de parametros;
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
