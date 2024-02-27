package com.einfochips.model;

public class RoomBookingRequest {
	
	private String roomId;
	private String startDate;
	private String endDate;
	private String customerName;
	
	
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public RoomBookingRequest(String roomId, String startDate, String endDate, String customerName) {
		super();
		this.roomId = roomId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.customerName = customerName;
	}
	public RoomBookingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RoomBookingRequest [roomId=" + roomId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", customerName=" + customerName + "]";
	}
	
	
	

}
