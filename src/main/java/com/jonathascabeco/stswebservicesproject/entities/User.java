package com.jonathascabeco.stswebservicesproject.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user") 
public class User implements Serializable{
	
//	Basic entity checklist:
//	 Basic attributes; ok
//	 Associations (instantiate collections); como é a primeira entidade não possui associações ainda;
//	 Constructors;
		//como esta sendo usado framework, obrigatorio colocar o vazio.
//	 Getters & Setters (collections: only get);
//	 hashCode & equals;
		//podem ser feitos para comparar qualquer critério(atributos da entidade), nesse, por padrão, instaurou-se o id;
//	 Serializable;
		//é implementado na class para que o objeto seja transformado em cadeia de bytes, 
		// objetavando ser trafegado em rede, gravado em arquivos etc;	
	
	
	private static final long serialVersionUID = 1L;
		
	@Id // chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	// gerando id automaticamente, essa declaração funciona na maioria dos banco de dados; 
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	//associações:
	//um para muitos (nome pego do UML):
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>(); 
	// instanciando a coleção(somente o get e nao o set, pois é uma coleção);
	
	public User(){
		
	}
	
	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public List<Order> getOrders() {
		return orders;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
