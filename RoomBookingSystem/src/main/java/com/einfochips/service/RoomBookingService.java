package com.einfochips.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einfochips.model.Reservation;
import com.einfochips.model.Room;
import com.einfochips.repo.ReservationRepo;
import com.einfochips.repo.RoomRepo;

@Service
public class RoomBookingService {
	
	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private ReservationRepo reservationRepo;
	
	public List<Room> getAvailableRoom()
	{
		return roomRepo.findByAvailableTrue();
	}
	
	public Boolean isRoomAvailable(Room room,LocalDate starDate,LocalDate endDate)
	{
		List<Reservation> conflictingReservation = (List<Reservation>) reservationRepo.findByRoomAndEndDateGreaterThanEqualAndStartDateLessThanEqual(room, starDate, endDate);
		return conflictingReservation.isEmpty();
	}
	
	public Reservation bookRoom(Room room,LocalDate statDate,LocalDate endDate,String customerName)
	{
		room.setAvailable(false);
		roomRepo.save(room);
		
		Reservation reservation = new Reservation();
		reservation.setRoom(room);
		reservation.setStartDate(statDate);
		reservation.setEndDate(endDate);
		reservation.setCustomerName(customerName);
		
		return reservationRepo.save(reservation);
		
	}
	
	public String cancelReservation(Reservation reservation)
	{
		reservation.getRoom().setAvailable(true);
		roomRepo.save(reservation.getRoom());
		reservationRepo.delete(reservation);
		return "Reservation Delete  successfully where Customer Name = " +reservation.getCustomerName()+" And Room Details is :n/"+reservation;
	}

}
