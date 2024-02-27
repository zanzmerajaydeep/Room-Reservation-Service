package com.einfochips.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Reservation {

	@Id
	private String id;

	@DBRef
	private Room room;
     
	private LocalDate startDate;
	private LocalDate endDate;
	private String customerName;
	
	
	
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", room=" + room + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", customerName=" + customerName + "]";
	}
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reservation(Room room, LocalDate startDate, LocalDate endDate, String customerName) {
		super();
		this.room = room;
		this.startDate = startDate;
		this.endDate = endDate;
		this.customerName = customerName;
	}
	public Reservation(String id, Room room, LocalDate startDate, LocalDate endDate, String customerName) {
		super();
		this.id = id;
		this.room = room;
		this.startDate = startDate;
		this.endDate = endDate;
		this.customerName = customerName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	

}
