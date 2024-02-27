package com.einfochips.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.einfochips.model.Room;

@EnableMongoRepositories
public interface RoomRepo  extends MongoRepository<Room, String>{
	List<Room> findByAvailableTrue();

	Optional<Room> findByRoomNumber(String roomNumber);
	
	@Query("{$and: [ { 'roomNumber': ?0 }, { 'capacity': ?1 }, { 'available': ?2 } ]}")
	List<Room> getByAttributes(String roomnumber,int capacity,Boolean available);
	
	 List<Room> findByRoomNumberAndCapacityAndAvailable(String roomNumber, int capacity, boolean available);
	 
	 List<Room> findByRoomNumberOrCapacityOrAvailable(String roomNumber, int capacity, boolean available);
	 
	 List<Room> findByCapacityGreaterThanEqual(int capacity);

	 
	 List<Room> findByCapacityLessThanEqualOrderByRoomNumberAsc(int capacity);

	 List<Room> findByRoomNumberIgnoreCaseLike(String roomNumber);
	 
	 List<Room> findByCapacityIn(List<Integer> capacities);


	

}
