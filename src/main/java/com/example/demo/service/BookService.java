package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.BookDto;

public interface BookService {
	
	public List<BookDto> getAllBooks();
	public BookDto getBookById(long id);
	public Map<String, Long> setBook(BookDto bookDto);
	public long deleteBook(Long id);
	public List<Long> deleteAllBooks();
	public BookDto updateBook(BookDto bookDto, long id);
	public void findBookByAuthorName(String name);
}
