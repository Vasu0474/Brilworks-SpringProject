package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;

import lombok.Singular;


public class AuthorDto {

	private long id;
	@NotEmpty(message = "first name cannot be empty")
	@Length(min = 1, max = 8, message = "First name cannot be more than 8 characters")
	private String fname;
	@NotEmpty(message = "last name cannot be empty")
	@Length(min = 1, max = 8, message = "Last name cannot be more than 8 characters")
	private String lname;
	@NotEmpty(message = "email cannot be empty")
	@Pattern(regexp = "^[a-z A-Z 0-9]{1,20}@[a-z A-Z 0-9]{3,}.[a-zA-Z]{2,5}$", message = "Entered email is not valid")
	@Size(min = 12,max = 30,message = "Size of email is not valid")
	private String email;
	@NotEmpty(message = "Address field cannot be empty")
	private String address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
