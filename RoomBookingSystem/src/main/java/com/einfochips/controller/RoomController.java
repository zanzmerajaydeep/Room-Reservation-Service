package com.einfochips.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.einfochips.exception.ResourceNotFoundException;
import com.einfochips.model.Reservation;
import com.einfochips.model.Room;
import com.einfochips.model.RoomBookingRequest;
import com.einfochips.repo.ReservationRepo;
import com.einfochips.repo.RoomRepo;
import com.einfochips.service.RoomBookingService;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomBookingService roomBookingService;
	
	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private ReservationRepo reservationRepo;
	
	@PostMapping("/addRoom")
	public String getAvailableRoom(@RequestBody Room  room)
	{
		 roomRepo.save(room);
		 return "room added!...";
	}
	
	@GetMapping("/getReservation")
	public List<Reservation> gegO( )
	{
		 return reservationRepo.findAll();
	}
	
	@GetMapping("/getByAttribute")
	public List<Room> gegO(@RequestParam String roomNumber,@RequestParam int  capacity,@RequestParam boolean available  )
	{
		System.out.println(roomNumber+"  "+capacity+""+available+"");
		 return roomRepo.getByAttributes(roomNumber, capacity, available);
	}
	
	@GetMapping("/getByAttribute2")
	public List<Room> gegO2(@RequestParam String roomNumber,@RequestParam int  capacity,@RequestParam boolean available  )
	{
		System.out.println(roomNumber+"  "+capacity+""+available+"");
		 return roomRepo.findByRoomNumberAndCapacityAndAvailable(roomNumber, capacity, available);
	}
	
	@GetMapping("/getByAttributeOr")
	public List<Room> gegO3(@RequestParam String roomNumber,@RequestParam int  capacity,@RequestParam boolean available  )
	{
		System.out.println(roomNumber+"  "+capacity+""+available+"");
		 return roomRepo.findByRoomNumberOrCapacityOrAvailable(roomNumber, capacity, available);
	}
	
	@GetMapping("/getcapacityIn")
	public List<Room> gegO3(@RequestParam int  capacity)
	{
		//System.out.println(roomNumber+"  "+capacity+""+available+"");
		List<Integer> list=new  ArrayList<Integer>();
		list.add(5);
		list.add(2);
		return roomRepo.findByCapacityIn(list);
	}

	@GetMapping("/getAvailableRoom")
	public List<Room> getAvailableRoom()
	{
		 return roomBookingService.getAvailableRoom();
	}
	
	@GetMapping("/getAvailableRoomByID")
	public Room getByRoomId(@RequestParam String id)
	{
		
		 return roomRepo.findByRoomNumber(id).orElseThrow(()-> new ResourceNotFoundException(id)); 
		// return bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException(bookingId));
	}
	
//	book Room...........
//	{
//	    "roomId": "B202",
//	    "startDate": "2023-07-06",
//	    "endDate": "2023-07-08",
//	    "customerName": "John Doe"
//	  }
	
	@PostMapping("/Book")
	public ResponseEntity<String> bookRoom(@RequestBody RoomBookingRequest roomBookingRequest) throws IllegalAccessException
	{
		
		Optional<Room> room= roomRepo.findByRoomNumber(roomBookingRequest.getRoomId());
		if (room==null) {
		    throw new IllegalArgumentException("Invalid room ID");
		}
		
		LocalDate startDate = LocalDate.parse(roomBookingRequest.getStartDate());
		LocalDate endDate = LocalDate.parse(roomBookingRequest.getEndDate());
		String customerName=roomBookingRequest.getCustomerName();
		
		if(Boolean.TRUE.equals(roomBookingService.isRoomAvailable(room, startDate, endDate)))
		{
			Reservation reservation=roomBookingService.bookRoom(room, startDate, endDate, customerName);
			return ResponseEntity.ok("Room Booked. Reservation ID : "+reservation.getId());
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Room not available for the requested dates.");
		}
	}
	
	@DeleteMapping("/cancelReservation")
	public ResponseEntity<String> cancelReservation(@RequestParam String reservationID) throws IllegalAccessException
	{
		Reservation reservation= reservationRepo.findById(reservationID).orElseThrow(()->new IllegalAccessException("invalid reservation ID"));
		
		String msg = roomBookingService.cancelReservation(reservation);
		
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	

}
