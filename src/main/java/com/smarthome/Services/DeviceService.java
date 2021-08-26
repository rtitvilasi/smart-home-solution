package com.smarthome.Services;

import org.springframework.stereotype.Service;

import com.smarthome.models.Device;
import com.smarthome.models.GenericResponse;

@Service
public interface DeviceService {
	
	public GenericResponse addDevice(Device device);
	public GenericResponse editDevice(Device device);
	public GenericResponse deleteDevice(String deviceId);
	public GenericResponse getAllDeviceByRoomId(String roomId);
	public GenericResponse getAllDeviceByUserId(String userId);
	public GenericResponse getDeviceById(String deviceId);
	public GenericResponse currentElectricityConsumption(String userId);
	public GenericResponse toggleFavouriteByDeviceId(String deviceId);
	public GenericResponse toggleFavouriteByRoomId(String roomId);
	public GenericResponse toggleFavouriteByUserId(String deviceId);
	public GenericResponse toggleDevice(String deviceId);
}
