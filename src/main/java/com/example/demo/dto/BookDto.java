package com.example.demo.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.example.demo.enumeration.BookType;


public class BookDto {
	private long id;

	@NotEmpty(message = "Title cannot be empty")
	private String title;

	@Min(value = 1, message = "price cannot be =< zero")
	private double price;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message = "date is mandetory")
	@Past(message = "invalid date")
	private LocalDate date;
	
	private BookType genres;
	// @Min(value = 1,message = "id should be more than zero")
	private long authorId;

	
	public BookType getGenres() {
		return genres;
	}

	public void setGenres(BookType genres) {
		this.genres = genres;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
  
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
