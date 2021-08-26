package com.smarthome.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "Rooms")
public class Room {

	@Id
	private String id;
	private String roomName;
	private List<Device> devices;
	private User user;
	
	
}
