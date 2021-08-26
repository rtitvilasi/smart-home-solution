package com.smarthome.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarthome.Services.RoomService;
import com.smarthome.models.GenericResponse;
import com.smarthome.models.Room;

@RestController
@RequestMapping("/room-api")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	
	@PostMapping("/room")
	public GenericResponse addRoom(@RequestBody Room room) {
		
		return roomService.addRoom(room);
		
	}
	
	@PutMapping("/room")
	public GenericResponse editRoom(@RequestBody Room room) {
		
		return roomService.editRoom(room);
		
	}
	
	@DeleteMapping("/room/{roomid}")
	public GenericResponse deleteRoom(@PathVariable("roomid") String roomId) {
		
		return roomService.deleteRoom(roomId);
		
	}
	
	@GetMapping("/room/{roomid}")
	public GenericResponse getRoomById(@PathVariable("roomid") String roomId) {
		
		return roomService.getRoomById(roomId);
		
	}
	
	@GetMapping("/rooms/{userid}")
	public GenericResponse getRoomByUserId(@PathVariable("userid") String userId) {
		
		return roomService.getRoomByUserId(userId);
		
	}
	
}
