package com.einfochips.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.einfochips.model.Reservation;
import com.einfochips.model.Room;

@EnableMongoRepositories
public interface ReservationRepo extends MongoRepository<Reservation, String> {
	
	List<Reservation> findByRoomAndEndDateGreaterThanEqualAndStartDateLessThanEqual(Room room, LocalDate startDate, LocalDate endDate);

   // List<Reservation> findByRoomAndEndDateGreaterThanEqualAndStartDateLessThanEqual1(Room room, 
   //		LocalDate starDate, LocalDate endDate);

}
