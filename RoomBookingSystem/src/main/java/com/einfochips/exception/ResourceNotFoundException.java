package com.einfochips.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String id) {
		super("Booking Not found in database with id: " + id);
	}

}
