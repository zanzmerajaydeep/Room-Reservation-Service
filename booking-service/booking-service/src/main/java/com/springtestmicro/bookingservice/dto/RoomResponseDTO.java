package com.springtestmicro.bookingservice.dto;

import lombok.Data;

@Data
public class RoomResponseDTO {
	private Long roomId;
	private String roomNumber;
    private String roomType;
    private double price;
    private boolean available;    
}

