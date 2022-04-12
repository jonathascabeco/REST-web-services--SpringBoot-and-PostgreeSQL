package com.jonathascabeco.stswebservicesproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonathascabeco.stswebservicesproject.entities.User;
import com.jonathascabeco.stswebservicesproject.repositories.UserRepository;

//@Component // registra como componente do spring para ser injetado automaticament com autowired
//@Repository// registrar um repository
@Service // registrar um servico no spring
public class UserService {

	@Autowired // fazer a injeção de dependencia de forma transparente ao programador;
	private UserRepository repository;
	//nao precisa colocar o @Repository nessa classe porque esta esta herdadando do JPA repository
	//ou seja ja esta no spring;
	
	// repassa para o repository, o services está fazendo o meio de campo entre o repository e REST;
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		//retorna um objeto optional, existe desde o java 8;
		return obj.get();
	}
	
	public User insert(User obj) {
		return repository.save(obj);// ja retorna o obj inserido no banco de dados;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
