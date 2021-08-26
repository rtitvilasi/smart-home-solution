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

import com.smarthome.Services.DeviceService;
import com.smarthome.models.Device;
import com.smarthome.models.GenericResponse;

@RestController
@RequestMapping("/device-api")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;
	
	
	
	@PostMapping("/device")
	public GenericResponse addDevice(@RequestBody Device device) {
		
		return deviceService.addDevice(device);
	}
	
	@PutMapping("/device")
	public GenericResponse editDevice(@RequestBody Device device) {
		
		return deviceService.editDevice(device);
	}
	
	@PutMapping("/device/toggle/{deviceid}")
	public GenericResponse toggleDevice(@PathVariable("deviceid") String deviceId) {
		
		return deviceService.toggleDevice(deviceId);
	}
	
	@DeleteMapping("/device/{deviceid}")
	public GenericResponse deleteDevice(@PathVariable("deviceid") String deviceId) {
		
		return deviceService.deleteDevice(deviceId);
	}
	
	@GetMapping("/devices/roomid/{roomid}")
	public GenericResponse getAllDeviceByRoomId(@PathVariable("roomid") String roomId) {
		
		return deviceService.getAllDeviceByRoomId(roomId);
		
	}
	
	@GetMapping("/devices/userid/{userid}")
	public GenericResponse getAllDeviceByUserId(@PathVariable("userid") String userId) {
		
		return deviceService.getAllDeviceByUserId(userId);
		
	}
	
	@GetMapping("/devices/deviceid/{deviceid}")
	public GenericResponse getDeviceById(@PathVariable("deviceid") String deviceId) {
		
		return deviceService.getDeviceById(deviceId);
		
	}
	
	@GetMapping("/devices/electricityconsumption/{userid}")
	public GenericResponse currentElectricityConsumption(@PathVariable("userid") String userId) {
		
		return deviceService.currentElectricityConsumption(userId);
		
	}
	
	@PostMapping("/devices/favourite/deviceid/{deviceid}")
	public GenericResponse toggleFavouriteByDeviceId(@PathVariable("deviceid") String deviceId) {
		
		return deviceService.toggleFavouriteByDeviceId(deviceId);
		
	}
	
	@PostMapping("/devices/favourite/roomid/{roomid}")
	public GenericResponse toggleFavouriteByRoomId(@PathVariable("roomid") String roomId) {
		
		return deviceService.toggleFavouriteByRoomId(roomId);
		
	}
	
	@PostMapping("/devices/favourite/userid/{userid}")
	public GenericResponse toggleFavouriteByUserId(@PathVariable("userid") String userId) {
		
		return deviceService.toggleFavouriteByUserId(userId);
		
	}

	
}
