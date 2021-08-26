package com.smarthome.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenericResponse {

	private boolean isValid;
	private int errorCode;
	private String errorMessage;
	private Object responseBody;
	
}
