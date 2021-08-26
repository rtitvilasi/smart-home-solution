package com.smarthome.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smarthome.exception.SmartNotFoundException;
import com.smarthome.models.GenericResponse;
import com.smarthome.models.Room;
import com.smarthome.models.User;
import com.smarthome.repository.RoomRepository;
import com.smarthome.repository.UserRepository;

@Component
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public GenericResponse addRoom(Room room) throws SmartNotFoundException {
		
        GenericResponse genericResponse = new GenericResponse();

			if(room.getRoomName()==null) {
				 throw new SmartNotFoundException("Room Name is Blank");
			}
			if(room.getUser().getUserId()==null) {
				throw new SmartNotFoundException("User id is blank");
			}
			
			User tmpUser = userRepo.findByuserId(room.getUser().getUserId());
			room.setUser(tmpUser);
			
			roomRepo.save(room);
			genericResponse.setValid(true);
			return genericResponse;
		
	}

	@Override
	public GenericResponse editRoom(Room room) throws SmartNotFoundException {
		
		 GenericResponse genericResponse = new GenericResponse();

				if(room.getRoomName()==null) {
					throw new SmartNotFoundException("Room Name is Blank");
				}
				if(room.getId()==null) {
					throw new SmartNotFoundException("Id is Blank");
				}
				
				Room roomtosave = roomRepo.findById(room.getId());
				if(roomtosave==null) {
					throw new SmartNotFoundException("Room Id is not correct");
				}
				roomtosave.setRoomName(room.getRoomName());
				roomRepo.save(roomtosave);
				genericResponse.setValid(true);
				return genericResponse;
	}

	@Override
	public GenericResponse deleteRoom(String roomId) throws SmartNotFoundException {
		
		GenericResponse genericResponse = new GenericResponse();

			if(roomId==null) {
				throw new SmartNotFoundException("Id is Blank");
			}
			
			Room roomtosave = roomRepo.findById(roomId);
			if(roomtosave==null) {
				throw new SmartNotFoundException("Room Id is not correct");
			}
			roomRepo.delete(roomtosave);
			genericResponse.setValid(true);
			return genericResponse;
		
	}

	@Override
	public GenericResponse getRoomById(String roomId) throws SmartNotFoundException {
		
        GenericResponse genericResponse = new GenericResponse();

			if(roomId==null) {
				throw new SmartNotFoundException("Id is Blank");
			}
			
			Room roomtosave = roomRepo.findById(roomId);
			if(roomtosave==null) {
				throw new SmartNotFoundException("Room Id is not correct");
			}
			genericResponse.setResponseBody(roomtosave);
			genericResponse.setValid(true);
			return genericResponse;
	}

	@Override
	public GenericResponse getRoomByUserId(String userId) throws SmartNotFoundException {
        GenericResponse genericResponse = new GenericResponse();
        
			if(userId==null) {
				throw new SmartNotFoundException("Id is Blank");
			}
			
			List<Room> roomtosave = roomRepo.findByUser_UserId(userId);
			if(roomtosave==null) {
				throw new SmartNotFoundException("Room does not exist for this user");
			}
			genericResponse.setResponseBody(roomtosave);
			genericResponse.setValid(true);
			return genericResponse;
	}

}
