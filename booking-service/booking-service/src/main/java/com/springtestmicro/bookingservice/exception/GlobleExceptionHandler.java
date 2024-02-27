package com.springtestmicro.bookingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springtestmicro.bookingservice.payload.ApiResponse;


@RestControllerAdvice
public class GlobleExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ApiResponse> handlerRomNotFoundException(ResourceNotFoundException exception) {
		
		String message = exception.getMessage();
		
		ApiResponse response = ApiResponse.builder().message(message).success(false).status(HttpStatus.NOT_FOUND).build();
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
