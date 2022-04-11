package com.jonathascabeco.stswebservicesproject.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jonathascabeco.stswebservicesproject.entities.Order;
import com.jonathascabeco.stswebservicesproject.entities.Product;

//No paradigma OO não tem conceito de chave primaria composta. O atributo identificador
//do objeto é um só. Por isso, precisa-se de uma classe auxiliar para representar as classes
//Product e Order, estas vão identificar o obj OrderItem(não possui chave primária própria
//		seja ela auto incrementável ou não);

@Embeddable // anotação para class chave primaria composta;
public class OrderItemPk implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	//a comparação tem de ter os dois atributos, pois compoem o item OrderItem;
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPk other = (OrderItemPk) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}	
	//nao possui construtores;	
}
