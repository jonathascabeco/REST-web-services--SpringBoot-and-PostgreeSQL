package com.jonathascabeco.stswebservicesproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathascabeco.stswebservicesproject.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	//instanciar obj repository que tem varias funcionalidades para interação com o usuario;
}
