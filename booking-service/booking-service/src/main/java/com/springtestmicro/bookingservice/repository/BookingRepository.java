package com.springtestmicro.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springtestmicro.bookingservice.model.Booking;


public interface BookingRepository extends JpaRepository<Booking, Long>{

}
