package com.smarthome.models;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiErrors {

	LocalDateTime timestamp;
	String message;
	HttpStatus status;
	
	
}