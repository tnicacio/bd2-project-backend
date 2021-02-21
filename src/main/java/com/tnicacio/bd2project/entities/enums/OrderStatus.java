package com.tnicacio.bd2project.entities.enums;

import java.util.Arrays;

public enum OrderStatus {
	
	CONFIRMADA("O"),
	CANCELADA("X");
	
	private String code;
	
	private OrderStatus(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static OrderStatus nameOfStatus(String code) {		
		return Arrays.stream(OrderStatus.values())
				.filter(value -> value.getCode().equals(code))
				.findFirst()
				.orElse(null);
	}

}
