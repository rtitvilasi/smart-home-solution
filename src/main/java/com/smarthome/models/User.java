package com.smarthome.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "Users")
public class User {

	@Id
	private String userId;
	private String name;
	private String password;
	private byte[] passwordHash;
	private String contact;
	private String email;
	private Role userRole;
    private String imageUrl;
    
	
}
