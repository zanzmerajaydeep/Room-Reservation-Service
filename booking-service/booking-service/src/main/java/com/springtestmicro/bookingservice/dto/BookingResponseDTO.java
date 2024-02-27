package com.springtestmicro.bookingservice.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class BookingResponseDTO {
	private Long bookingId;
	private Long userId;
	private Long roomId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int days;
	private RoomResponseDTO room;
}
