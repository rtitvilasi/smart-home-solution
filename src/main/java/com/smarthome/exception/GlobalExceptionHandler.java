package com.smarthome.exception;


import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.smarthome.models.GenericResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String message = ex.getMessage();
		headers.add("desc", "Http method not supported");
		GenericResponse errors = new GenericResponse();
		errors.setErrorCode(100);
		errors.setErrorMessage(message);
		errors.setValid(false);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String message = ex.getMessage();
		headers.add("desc", "Media Type not supported");
		GenericResponse errors = new GenericResponse();
		errors.setErrorCode(101);
		errors.setErrorMessage(message);
		errors.setValid(false);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String message = ex.getMessage();
		headers.add("desc", "Path variable is missing");
		GenericResponse errors = new GenericResponse();
		errors.setErrorCode(103);
		errors.setErrorMessage(message);
		errors.setValid(false);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String message = ex.getMessage();
		headers.add("desc", "Invalid Data type");
		GenericResponse errors = new GenericResponse();
		errors.setErrorCode(104);
		errors.setErrorMessage(message);
		errors.setValid(false);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}

	@ExceptionHandler(SmartNotFoundException.class)
	public ResponseEntity<Object> handleHotelNotFound(SmartNotFoundException ex){
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Not Found");
		String message = ex.getMessage();
		GenericResponse errors = new GenericResponse();
		errors.setErrorCode(105);
		errors.setErrorMessage(message);
		errors.setValid(false);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(errors);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex){
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Exception occured");
		String message = ex.getMessage();
		GenericResponse errors = new GenericResponse();
		errors.setErrorCode(106);
		errors.setErrorMessage(message);
		errors.setValid(false);
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).headers(headers).body(errors);
	}
}