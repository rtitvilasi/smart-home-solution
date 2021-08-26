package com.smarthome.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smarthome.models.Room;

public interface RoomRepository extends MongoRepository<Room, Integer>{
	
	public Room findById(String id);
	public List<Room> findByUser_UserId(String userId);

}
