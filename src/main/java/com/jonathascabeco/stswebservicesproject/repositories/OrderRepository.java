package com.jonathascabeco.stswebservicesproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathascabeco.stswebservicesproject.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
