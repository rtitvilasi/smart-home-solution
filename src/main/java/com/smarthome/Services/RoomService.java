package com.smarthome.Services;

import org.springframework.stereotype.Service;

import com.smarthome.models.GenericResponse;
import com.smarthome.models.Room;

@Service
public interface RoomService {
	
	public GenericResponse addRoom(Room room);
	public GenericResponse editRoom(Room room);
	public GenericResponse deleteRoom(String roomId);
	public GenericResponse getRoomById(String roomId);
	public GenericResponse getRoomByUserId(String userId);

}
