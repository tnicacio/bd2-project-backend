package com.tnicacio.bd2project.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
	
	public ResourceNotFoundException(Object vid, Object pid) {
		super("Resource not found. Order id " + vid + " | Product id + " + pid);
	}

}
