package com.jonathascabeco.stswebservicesproject.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jonathascabeco.stswebservicesproject.entities.enums.OrderStatus;

@Entity
@Table(name = "tb_order") 
//para nao dar conflito entre o nome da classe e nomes reservados do SQL;
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	// a partir do Java 8 começou-se a utilizar a classe instant(muito melhor) ao invés de Date;	
	
	//enum
	//tratamento integer somente dentro da classe, mas pro mundo externo é OrderStatus;
	private Integer orderStatus;	
		
	//associação de chave estrangeira via JPA;
	@ManyToOne
	@JoinColumn(name = "client_id")
	//associações(seguir nomenclatura):
	//muitos para um;
	private User client;
	
	//construtores:
	
	public Order() {		
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus); //convertendo numero inteiro da classe em OrderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public void setClient(User client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}	
	
}
