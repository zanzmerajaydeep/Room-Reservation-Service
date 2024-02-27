package com.springtestmicro.bookingservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtestmicro.bookingservice.exception.ResourceNotFoundException;
import com.springtestmicro.bookingservice.model.Booking;
import com.springtestmicro.bookingservice.repository.BookingRepository;
import com.springtestmicro.bookingservice.service.BookingService;
@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	BookingRepository bookingRepository;
	
	@Override
	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> getAllBooking() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking getBooking(Long bookingId) {
		return bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException(bookingId));
	}

}
