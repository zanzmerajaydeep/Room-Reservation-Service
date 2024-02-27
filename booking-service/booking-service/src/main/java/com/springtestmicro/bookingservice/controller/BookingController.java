package com.springtestmicro.bookingservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springtestmicro.bookingservice.dto.BookingResponseDTO;
import com.springtestmicro.bookingservice.dto.RoomResponseDTO;
import com.springtestmicro.bookingservice.model.Booking;
import com.springtestmicro.bookingservice.service.BookingService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingController {
	
	BookingService bookingService;
	
	RestTemplate restTemplate;
	
	private static final String ROOMSERVICEURL = "http://localhost:8081/api/rooms";

	
	@PostMapping
	public ResponseEntity<?> createBooking(@RequestBody Booking booking){

		RoomResponseDTO roomcheck = restTemplate.getForObject(ROOMSERVICEURL + "/" + booking.getRoomId(), RoomResponseDTO.class);
	    
	    if (!roomcheck.isAvailable()) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("Room is not available for booking");
	    }
		
		
		
		Booking newBooking = bookingService.createBooking(booking);
		
		BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();

		bookingResponseDTO.setBookingId(newBooking.getBookingId());
		bookingResponseDTO.setUserId(newBooking.getUserId());
		bookingResponseDTO.setRoomId(newBooking.getRoomId());
		bookingResponseDTO.setCheckInDate(newBooking.getCheckInDate());
		bookingResponseDTO.setCheckOutDate(newBooking.getCheckOutDate());
		bookingResponseDTO.setDays(newBooking.getDays());
		
		RoomResponseDTO room = restTemplate.getForObject(ROOMSERVICEURL+"/"+newBooking.getRoomId(), RoomResponseDTO.class);
		
		room.setAvailable(false);
		
		restTemplate.put(ROOMSERVICEURL+"/"+newBooking.getRoomId(), room);
		
		bookingResponseDTO.setRoom(room);
				
		return ResponseEntity.status(HttpStatus.CREATED).body(bookingResponseDTO);
	}
	
	
	@GetMapping("/{bookingId}")
	public ResponseEntity<BookingResponseDTO> getRoom(@PathVariable Long bookingId){
		Booking booking = bookingService.getBooking(bookingId);
		
		BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
		
		bookingResponseDTO.setBookingId(booking.getBookingId());
		bookingResponseDTO.setUserId(booking.getUserId());
		bookingResponseDTO.setRoomId(booking.getRoomId());
		bookingResponseDTO.setCheckInDate(booking.getCheckInDate());
		bookingResponseDTO.setCheckOutDate(booking.getCheckOutDate());
		bookingResponseDTO.setDays(booking.getDays());
		
		RoomResponseDTO room = restTemplate.getForObject(ROOMSERVICEURL+"/"+booking.getRoomId(), RoomResponseDTO.class);
	    bookingResponseDTO.setRoom(room);
		
		return ResponseEntity.ok(bookingResponseDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<BookingResponseDTO>> getAllBookings(){
		List<Booking> bookings = bookingService.getAllBooking();
		
		List<BookingResponseDTO> bookingResponseDTOList = new ArrayList<>();
		
		
		for (Booking booking : bookings) {
			BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
			
			bookingResponseDTO.setBookingId(booking.getBookingId());
			bookingResponseDTO.setUserId(booking.getUserId());
			bookingResponseDTO.setRoomId(booking.getRoomId());
			bookingResponseDTO.setCheckInDate(booking.getCheckInDate());
			bookingResponseDTO.setCheckOutDate(booking.getCheckOutDate());
			bookingResponseDTO.setDays(booking.getDays());
						
			bookingResponseDTOList.add(bookingResponseDTO);
		}
				
		return ResponseEntity.ok(bookingResponseDTOList);
	}
	
}
