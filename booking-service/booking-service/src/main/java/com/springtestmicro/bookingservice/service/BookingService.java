package com.springtestmicro.bookingservice.service;

import java.util.List;

import com.springtestmicro.bookingservice.model.Booking;

public interface BookingService {
	Booking createBooking(Booking booking);

	List<Booking> getAllBooking();

	Booking getBooking(Long bookingId);

}
