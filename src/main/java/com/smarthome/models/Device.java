package com.smarthome.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "Devices")
public class Device {

	@Id
	private String id;
	private deviceType deviceType;
	private boolean deviceStatus;
	private int maxConsumption;
	private int currentConsumption;
	private String color;
	private int favouriteIntensity;
	private String favouriteColor;
	private String roomId;
	private String userId;
	

	public enum deviceType{
		socket,
		light,
		fan
	}
	
}
