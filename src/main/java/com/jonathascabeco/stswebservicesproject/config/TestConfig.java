package com.jonathascabeco.stswebservicesproject.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jonathascabeco.stswebservicesproject.entities.User;
import com.jonathascabeco.stswebservicesproject.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	// injeção de dependencia implicito do proprio framework, ou seja, nao precisa
	// set ou construtor;
	@Autowired
	private UserRepository userRepository;

	//metodo para instanciar o bd;
	@Override
	public void run(String... args) throws Exception {
		//nuul no id porque está sendo gerado automaticamente;
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepository.saveAll(Arrays.asList(u1,u2));
	}

}
