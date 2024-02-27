package com.einfochips.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "room")
public class Room {

	@Id
	private String id;

	private String roomNumber;
	private int capacity;
	private boolean available;
	
	
	
	@Override
	public String toString() {
		return "Room [id=" + id + ", roomNumber=" + roomNumber + ", capacity=" + capacity + ", available=" + available
				+ "]";
	}
	public Room(String roomNumber, int capacity, boolean available) {
		super();
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.available = available;
	}
	public Room(String id, String roomNumber, int capacity, boolean available) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.available = available;
	}
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
//	public IllegalArgumentException orElseThrow(String msg)
//	{
//		// TODO Auto-generated method stub
//		return new IllegalArgumentException(msg);
//	}
	
	

}
