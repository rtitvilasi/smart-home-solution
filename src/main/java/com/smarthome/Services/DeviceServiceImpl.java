package com.smarthome.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smarthome.exception.SmartNotFoundException;
import com.smarthome.models.Device;
import com.smarthome.models.Device.deviceType;
import com.smarthome.repository.DeviceRepository;
import com.smarthome.repository.RoomRepository;
import com.smarthome.models.GenericResponse;

@Component
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private RoomRepository roomRepo;
	
	
	@Autowired
	private DeviceRepository deviceRepo;
	
	@Override
	public GenericResponse addDevice(Device device) throws SmartNotFoundException{
		
        GenericResponse genericResponse = new GenericResponse();

			if(device.getDeviceType()==null) {
				throw new SmartNotFoundException("DeviceType is Blank");
			}
			if(device.getMaxConsumption()==0) {
				throw new SmartNotFoundException("Invalid Max Consumption");
			}
			if(device.getRoomId()==null) {
				throw new SmartNotFoundException("Room Id is Blank");
			}
			
			device.setUserId(roomRepo.findById(device.getRoomId()).getUser().getUserId());
			device.setDeviceStatus(false);
			device.setCurrentConsumption(0);
			if(device.getDeviceType().equals(deviceType.light)) {
				device.setColor("#000000");
			}
			
			
			deviceRepo.save(device);
			genericResponse.setValid(true);
			return genericResponse;
	}

	@Override
	public GenericResponse editDevice(Device device) throws SmartNotFoundException{
		
       GenericResponse genericResponse = new GenericResponse();
			if(device.getDeviceType()==null) {
				throw new SmartNotFoundException("DeviceType is Blank");
			}
			if(device.getId()==null) {
				throw new SmartNotFoundException("Device Id is Blank");
			}
			if(device.getMaxConsumption()==0) {
				throw new SmartNotFoundException("Invalid Max Consumption");
			}
			if(device.getRoomId()==null) {
				throw new SmartNotFoundException("Room Id is Blank");
			}
			
			Device devicetosave = deviceRepo.findByid(device.getId());
			devicetosave.setMaxConsumption(device.getMaxConsumption());
			devicetosave.setCurrentConsumption(device.getCurrentConsumption());
			if(devicetosave.getDeviceType().equals(deviceType.light)) {
				devicetosave.setColor("#000000");
				devicetosave.setFavouriteColor(device.getFavouriteColor());
			}
			devicetosave.setFavouriteIntensity(device.getFavouriteIntensity());
			
			deviceRepo.save(devicetosave);
			genericResponse.setValid(true);
			return genericResponse;
	}

	@Override
	public GenericResponse deleteDevice(String deviceId) throws SmartNotFoundException{

		GenericResponse genericResponse = new GenericResponse();

			if(deviceId==null) {
				throw new SmartNotFoundException("Device ID is Blank");
			}
			
			Device devicetosave = deviceRepo.findByid(deviceId);
			deviceRepo.delete(devicetosave);
			genericResponse.setValid(true);
			return genericResponse;
	}

	@Override
	public GenericResponse getAllDeviceByRoomId(String roomId) throws SmartNotFoundException{

		GenericResponse genericResponse = new GenericResponse();
		
			if(roomId==null) {
				throw new SmartNotFoundException("Room ID is Blank");
			}
			
			List<Device> devicetosave = deviceRepo.findByroomId(roomId);
			genericResponse.setResponseBody(devicetosave);
			genericResponse.setValid(true);
			return genericResponse;
	}

	@Override
	public GenericResponse getAllDeviceByUserId(String userId) throws SmartNotFoundException {

		GenericResponse genericResponse = new GenericResponse();
		
		
			if(userId==null) {
				throw new SmartNotFoundException("User ID is Blank");
			}
			
			List<Device> devicetosave = deviceRepo.findByuserId(userId);
			genericResponse.setResponseBody(devicetosave);
			genericResponse.setValid(true);
			return genericResponse;

	}

	@Override
	public GenericResponse currentElectricityConsumption(String userId) throws SmartNotFoundException {

		GenericResponse genericResponse = new GenericResponse();
		
			if(userId==null) {
				throw new SmartNotFoundException("User ID is Blank");
			}
			
			List<Device> devicetosave = deviceRepo.findByuserId(userId);
			int sum = 0;
			for (Device device : devicetosave) {
				sum += device.getCurrentConsumption();
			}
			genericResponse.setResponseBody(sum);
			genericResponse.setValid(true);
			return genericResponse;
		
	}

	@Override
	public GenericResponse toggleFavouriteByDeviceId(String deviceId) throws SmartNotFoundException {

		GenericResponse genericResponse = new GenericResponse();
		
			
			if(deviceId==null) {
				throw new SmartNotFoundException("Device Id is Blank");
			}
			
			Device devicetosave = deviceRepo.findByid(deviceId);
			
			if(devicetosave.getDeviceType().equals(deviceType.light)) {
				devicetosave.setColor(devicetosave.getFavouriteColor());
			}
			devicetosave.setCurrentConsumption(devicetosave.getFavouriteIntensity());
			
			deviceRepo.save(devicetosave);
			genericResponse.setValid(true);
			return genericResponse;
		
	}

	@Override
	public GenericResponse toggleFavouriteByRoomId(String roomId) throws SmartNotFoundException {

		GenericResponse genericResponse = new GenericResponse();
		
			if(roomId==null) {
				throw new SmartNotFoundException("Room Id is Blank");
			}
			
			List<Device> devicetosave = deviceRepo.findByroomId(roomId);
			
			for (Device device : devicetosave) {
				
				if(device.getDeviceType().equals(deviceType.light)) {
					device.setColor(device.getFavouriteColor());
				}
				device.setCurrentConsumption(device.getFavouriteIntensity());
				
			}
			
			deviceRepo.saveAll(devicetosave);
			genericResponse.setValid(true);
			return genericResponse;
		
	}

	@Override
	public GenericResponse toggleFavouriteByUserId(String userId) throws SmartNotFoundException {

		GenericResponse genericResponse = new GenericResponse();

			
			if(userId==null) {
				throw new SmartNotFoundException("User Id is Blank");
			}
			
			List<Device> devicetosave = deviceRepo.findByuserId(userId);
			
			for (Device device : devicetosave) {
				
				if(device.getDeviceType().equals(deviceType.light)) {
					device.setColor(device.getFavouriteColor());
				}
				device.setCurrentConsumption(device.getFavouriteIntensity());
				
			}
			
			deviceRepo.saveAll(devicetosave);
			genericResponse.setValid(true);
			return genericResponse;

	}

	@Override
	public GenericResponse getDeviceById(String deviceId) throws SmartNotFoundException {

		GenericResponse genericResponse = new GenericResponse();
		
			if(deviceId==null) {
				throw new SmartNotFoundException("Device Id is Blank");
			}
			
			Device devicetosave = deviceRepo.findByid(deviceId);
			
			genericResponse.setValid(true);
			genericResponse.setResponseBody(devicetosave);
			return genericResponse;

	}

	@Override
	public GenericResponse toggleDevice(String deviceId) throws SmartNotFoundException {
		
		GenericResponse genericResponse = new GenericResponse();
		
			if(deviceId==null) {
				throw new SmartNotFoundException("Device Id is Blank");
			}
			
			Device devicetosave = deviceRepo.findByid(deviceId);
			devicetosave.setDeviceStatus(!devicetosave.isDeviceStatus());
			deviceRepo.save(devicetosave);
			genericResponse.setValid(true);
			return genericResponse;

	}

}
