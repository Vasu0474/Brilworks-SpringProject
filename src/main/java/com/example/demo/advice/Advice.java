package com.example.demo.advice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class Advice {
	
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<?> invalidFormatException(InvalidFormatException invalidFormatException, WebRequest webrequest) {
		return new ResponseEntity<String>(invalidFormatException.getOriginalMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> conflict(DataIntegrityViolationException e)
	{
		return new ResponseEntity<String>("Email already exist...",HttpStatus.BAD_REQUEST);
	}
	
}
