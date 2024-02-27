package com.springtestmicro.bookingservice.exception;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(Long id) {
		super("Booking Not found in database with id: " + id);
	}
}
