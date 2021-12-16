package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.enumeration.BookType;
import com.example.demo.timeStamp.TimeStamp;

@Entity
@Table(name = "books")
public class Book extends TimeStamp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "published_date")
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genre")
	private BookType genres;

	@ManyToOne
	@JoinColumn(name = "author_fk")
	private Author author;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author object) {
		this.author = object;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", price=" + price + ", date=" + date + ", author=" + author
				+ "]";
	}

}
