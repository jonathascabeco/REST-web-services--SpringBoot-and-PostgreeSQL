package com.jonathascabeco.stswebservicesproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathascabeco.stswebservicesproject.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	//instanciar obj repository que tem varias funcionalidades para interação com o usuario;
}
