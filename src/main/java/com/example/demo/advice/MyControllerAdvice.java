package com.example.demo.advice;

import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.CustomException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> map = new HashMap<>();

		List<ObjectError> list = ex.getBindingResult().getAllErrors();
		for (int i = 0; i < list.size(); i++) {
			String fieldName = ((FieldError) list.get(i)).getField();
			map.put(fieldName, list.get(i).getDefaultMessage());
		}
		map.put("Status", status.toString());
		map.put("Timestamp", new Date().toString());

		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> invalidInput(CustomException errorDetails, WebRequest webrequest) {
		Map<String, String> map = new HashMap<>();
		map.put("message", errorDetails.getMessage());
		map.put("timestamp", new Date().toString());
		map.put("Status", HttpStatus.NOT_FOUND.toString());

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
	}
	
    
}
	
	


