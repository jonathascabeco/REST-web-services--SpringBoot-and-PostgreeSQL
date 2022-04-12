package com.jonathascabeco.stswebservicesproject.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jonathascabeco.stswebservicesproject.entities.User;
import com.jonathascabeco.stswebservicesproject.repositories.UserRepository;
import com.jonathascabeco.stswebservicesproject.services.exceptions.DatabaseException;
import com.jonathascabeco.stswebservicesproject.services.exceptions.ResourceNotFoundException;

//@Component // registra como componente do spring para ser injetado automaticament com autowired
//@Repository// registrar um repository
@Service // registrar um servico no spring
public class UserService {

	@Autowired // fazer a injeção de dependencia de forma transparente ao programador;
	private UserRepository repository;
	// nao precisa colocar o @Repository nessa classe porque esta esta herdadando do
	// JPA repository
	// ou seja ja esta no spring;

	// repassa para o repository, o services está fazendo o meio de campo entre o
	// repository e REST;
	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		// retorna um objeto optional, existe desde o java 8;
		// lança exceção quando nao houver nenhum usuario no optional
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
		// vai tentar fazer o get se nao conseguir manda exceção;
	}

	public User insert(User obj) {
		return repository.save(obj);// ja retorna o obj inserido no banco de dados;

	}

	public void delete(Long id) {
		// primeiro fez um try catch com RuntimeException, deu um clean, pegou o erro
		// especifico e implementou;
		try {
			repository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {//
			//e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

	public User update(Long id, User obj) {
		try {
		User entity = repository.getById(id);
		// instancia o usuario mas nao vai no bd, vai deixar um objeto monitorado pelo
		// jpa,
		// para posteriomente instanciar algo no bd, o getone ele pega o obj e espera,
		// di
		// ferente do findById que tem de entrar no bd e traze-lo para app;
		updateData(entity, obj);
		return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());

	}
}
