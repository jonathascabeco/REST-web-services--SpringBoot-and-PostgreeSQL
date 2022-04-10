package com.jonathascabeco.stswebservicesproject.entities.enums;

public enum OrderStatus {
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;
	
	//construtor diferente no enum, ele é especial;
	private OrderStatus(int code) {
		this.code = code;
	}
	
	//acessivel ao mundo exterior(outras classes);
	public int getCode() {
		return code;
	}
	
	//converter um valor numerico para um tipo enumerado; static porque vai funcionar sem precisar instanciar
	//enum correspondente ao code;
	public static OrderStatus valueOf(int code) {
		for(OrderStatus value : OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}			
		}
		throw new IllegalArgumentException("Invalid OrderStatus code!");
	}
}
