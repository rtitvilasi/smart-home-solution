package com.smarthome.exception;

public class SmartNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public SmartNotFoundException() {
		super();
	}
	public SmartNotFoundException(String message) {
		super(message);
	}

}
