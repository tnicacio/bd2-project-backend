package com.tnicacio.bd2project.entities.enums;

import java.util.Arrays;

public enum UserRole {
	
	UNDEFINED(0),
	ADMIN(1),
	CLIENT(2);
	
	private int code;
	
	private UserRole(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static UserRole valueOf(int code) {		
		return Arrays.stream(UserRole.values())
				.filter(value -> value.getCode() == code)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Ivalid UserRole code"));
	}

}
