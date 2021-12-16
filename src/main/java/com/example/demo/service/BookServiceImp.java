package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AuthorRepository;
import com.example.demo.dao.BookRepository;
import com.example.demo.dto.BookDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.exception.CustomException;

@Service
public class BookServiceImp implements BookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	Logger log = LoggerFactory.getLogger(BookServiceImp.class);
	
	public List<BookDto> getAllBooks() {
		log.info("inside getAllBooks method");
		List<Book> list = bookRepository.findAll();
		if (list.isEmpty()) {
			throw new CustomException("there are no records present");
		} 	
		List<BookDto> bookDtoList=list.stream().map((Book e)->new ModelMapper().map(e, BookDto.class)).collect(Collectors.toList());
		return bookDtoList;
	}

	@Cacheable(cacheNames = "book", key = "#id")
	public BookDto getBookById(long id) {
		log.info("inside getBookById method");
		Book book = bookRepository.findById(id);
		if (book == null) {
			throw new CustomException("no such book present with this id");
		}

		BookDto bookDto = new ModelMapper().map(book, BookDto.class);
		return bookDto;
	}

	public Map<String, Long> setBook(BookDto bookDto) {
		log.info("inside setBook method");
		Map<String, Long> map = new HashMap<>();
		Book book = new Book();
		Author author = authorRepository.findById(bookDto.getAuthorId());
//		if (author == null) {
//			throw new CustomException("No such authorId is present");
//		}
		book.setAuthor(author);
		book.setTitle(bookDto.getTitle());
		book.setPrice(bookDto.getPrice());
		book.setDate(bookDto.getDate());
		book.setGenres(bookDto.getGenres());
		
		book = bookRepository.save(book);
		log.info("book saved sucessfully");
		map.put("id", book.getId());
		return map;
	}

	@CacheEvict(cacheNames = "book", key = "#id", allEntries = false)
	public long deleteBook(Long id) {
		log.info("inside deleteBook method");
		Book book = bookRepository.findById(id).get();
		bookRepository.deleteById(id);
		return book.getId();
	}

	public List<Long> deleteAllBooks() {
		log.info("inside deleteAllBooks method");
		List<Book> list = (List<Book>) bookRepository.findAll();
		List<Long> idList = new ArrayList<>();
		if (list.isEmpty()) {
			throw new CustomException("Books records is already empty");
		}
		for (int i = 0; i < list.size(); i++) {
			long id = list.get(i).getId();
			deleteBook(id);
			idList.add(id);
		}
		return idList;
	}

	@CachePut(cacheNames = "book", key = "#id")
	public BookDto updateBook(BookDto bookDto, long id) {
		log.info("inside updateBook method");
		Book book = bookRepository.findById(id);
		if (book == null) {
			throw new CustomException("No book present with such Id");
		}
		Author author = authorRepository.findById(bookDto.getAuthorId());
		if (author == null) {
			throw new CustomException("authorId not found");
		}

		book.setAuthor(author);
		book.setTitle(bookDto.getTitle());
		book.setPrice(bookDto.getPrice());
		book.setDate(bookDto.getDate());
		book.setGenres(bookDto.getGenres());
		bookRepository.save(book);
		return new ModelMapper().map(book, BookDto.class);
	}

	public void findBookByAuthorName(String name)
	{
		List<Book> list=bookRepository.findBooksByAuthorName(name);
		System.out.println(list.toString());
	}
}
